package com.appcms.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.appcms.entity.CanjeProducto;
import com.appcms.entity.CustomerInscripcion;
import com.appcms.entity.CustomerReward;
import com.appcms.entity.CustomerRewardResponse;
import com.appcms.entity.Scotiauser;
import com.appcms.entity.Tarjetas;
import com.appcms.entity.UserCartola;
import com.appcms.entity.UserCartolaMovimiento;
import com.appcms.entity.UserCupon;
import com.appcms.entity.UserGusto;
import com.appcms.entity.customer.Customer;
import com.appcms.entity.points.ExpiringPoints;
import com.appcms.entity.points.Points;

@Service
public class CustomerService {

	private String apiUrl;	
	SimpleDateFormat formatter;
	Locale locale;
	
	private final static Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	public CustomerService(@Qualifier("apiUrl") String apiUrl) {
		this.apiUrl = apiUrl;
		this.locale = new Locale("es", "ES");
		this.formatter = new SimpleDateFormat("'al' dd 'de' MMMM 'de' yyyy", locale);
	}
	
	public List<CustomerInscripcion> loadUserInscripciones() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());
		try {
			ResponseEntity<List<CustomerInscripcion>> inscripcionesResponseEntity = restTemplate.exchange(apiUrl + "/v1/customer/" + credencialesEntity.getScotiauser().getIdCliente() + "/inscripciones",
					HttpMethod.GET, new HttpEntity<Object>(httpHeaders), 
					new ParameterizedTypeReference<List<CustomerInscripcion>>() {
					});
			return inscripcionesResponseEntity.getBody();
		} catch (Exception e) {
			logger.error("Error cargando las inscripciones de cliente: " + e.getMessage());
			return new ArrayList<>();
		}
	}
	
	public Points loadUserPoints() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());
		try {
			ResponseEntity<Points> pointsResponse = restTemplate.exchange(apiUrl + "/v1/customer/points", HttpMethod.GET, new HttpEntity<Object>(httpHeaders), Points.class);
			Points points = pointsResponse.getBody();
			try {
				// Se formatea la información que se va a mostrar
				String puntosDisponibles = String.format(locale, "%,d", Integer.parseInt(points.getAvailablePoints()));
				points.setAvailablePoints(puntosDisponibles);
				String puntosInscritos = String.format(locale, "%,d", Integer.parseInt(points.getRegisteredPoints()));
				points.setRegisteredPoints(puntosInscritos);
				String puntosPorVencer = String.format(locale, "%,d", Integer.parseInt(points.getExpiringPoints().getPoints()));
				points.getExpiringPoints().setPoints(puntosPorVencer);
				return points;
			} catch (Exception e) {
				logger.error("Error formateando puntos de cliente: " + e.getMessage() + ". Se pasarán como fueron recibidos");
				return points;
			}			
		} catch (Exception e) {
			logger.error("Error cargando puntos de cliente: " + e.getMessage());
			Points points = new Points();
			points.setAvailablePoints("-");
			ExpiringPoints expiringPoints = new ExpiringPoints();
			expiringPoints.setPoints("-");
			expiringPoints.setExpirationDate("N/A");
			points.setExpiringPoints(expiringPoints);
			points.setRegisteredPoints("-");
			return points;
		}
	}
	
	public UserCartola loadUserCartola() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		Scotiauser scotiauser = credencialesEntity.getScotiauser();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());

		/* SE RECUPERAN LOS PUNTOS DE CLIENTE */
		Points points = this.loadUserPoints();

		/* SE RECUPERAN LOS MOVIMIENTOS DE CLIENTE */
		List<UserCartolaMovimiento> movimientos = new ArrayList<>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		try {
			ResponseEntity<List<UserCartolaMovimiento>> movementsResponseEntity = restTemplate.exchange(
					apiUrl + "/v1/customer/transactions?year=" + year, HttpMethod.GET,
					new HttpEntity<Object>(httpHeaders), new ParameterizedTypeReference<List<UserCartolaMovimiento>>() {
					});
			movimientos = movementsResponseEntity.getBody();
		} catch (Exception e) {
			logger.error("Error cargando la cartola del cliente: " + e.getMessage());
			movimientos.clear();
		}
		// Se coloca la fecha actual a la cartola
		Date date = new Date(System.currentTimeMillis());
		String fechaActual = formatter.format(date);
		
		UserCartola miCartola = new UserCartola();
		miCartola.setNombre(scotiauser.getFirstname());
		miCartola.setApellido(scotiauser.getLastname());
		miCartola.setStrFecha(fechaActual);
		miCartola.setPuntosDisponibles(points.getAvailablePoints());
		miCartola.setPuntosPorVencer(points.getExpiringPoints().getPoints());
		miCartola.setFechaVencimiento(points.getExpiringPoints().getExpirationDate());
		miCartola.setPuntosInscritos(points.getRegisteredPoints());
		miCartola.setMovimientos(movimientos);
		return miCartola;
	}
	
	public List<UserCupon> loadCupones() {
		HttpHeaders headers = new HttpHeaders();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		headers.set("AuthorizationCustomer", credencialesEntity.getJwt());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		String url = apiUrl + "/v1/customer/" + credencialesEntity.getScotiauser().getIdCliente() + "/cupones" ;
		try {
			ResponseEntity<List<UserCupon>> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
					new ParameterizedTypeReference<List<UserCupon>>() {
					});
			return xresponse.getBody();
		} catch (Exception e) {
			logger.error("Error cargando cupones del cliente: " + e.getMessage());
			return new ArrayList<>();
		}
	}
	
	public byte[] loadCuponAsPdf(int idUser, int idReward) {
		HttpHeaders headers = new HttpHeaders();		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		headers.set("AuthorizationCustomer", credencialesEntity.getJwt());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		String url = apiUrl + "/v1/customer/" + idUser + "/cupones/" + idReward + "/getAsPdf";
		ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, byte[].class);
		return response.getBody();
	}
	
	public Tarjetas loadUserTarjetas() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Customer credencialesEntity = (Customer) auth.getPrincipal();
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());
			ResponseEntity<Tarjetas> tarjetasResponseEntity = restTemplate.exchange(apiUrl + "/v1/customer/cards",
					HttpMethod.GET, new HttpEntity<Object>(httpHeaders), Tarjetas.class);
			return tarjetasResponseEntity.getBody();
		} catch (Exception e) {
			logger.error("Error cargando las tarjetas del cliente: " + e.getMessage());
			Tarjetas tarjetas = new Tarjetas();
			tarjetas.setTarjetasCliente(new ArrayList<>());
			tarjetas.setTipoCliente("NORMAL");
			return tarjetas;
		}
	}
	
	public List<UserGusto> loadCustomerGustos() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());
		int id = credencialesEntity.getScotiauser().getIdCliente();
		try {
			ResponseEntity<List<UserGusto>> response = restTemplate.exchange(apiUrl + "/v1/customer/" + id + "/customer_gustos", HttpMethod.GET, new HttpEntity<Object>(httpHeaders), new ParameterizedTypeReference<List<UserGusto>>() {});
			return response.getBody();
		} catch (Exception e) {
			logger.error("Error cargando los gustos del cliente: " + e.getMessage());
			return new ArrayList<>();
		}
	}

	public void saveCustomerGustos(String[] gustos) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());
		int id = credencialesEntity.getScotiauser().getIdCliente();

		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		for (int i = 0; i < gustos.length; i++) {
			map.add("gusto", gustos[i]);
		}
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,
				httpHeaders);
		try {
			restTemplate
					.postForEntity(apiUrl + "/v1/customer/" + id + "/customer_gustos/save", request, String.class);
		} catch (Exception e) {
			logger.error("Error guardando los gustos del cliente: " + e.getMessage());
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public String getDespegarLink() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());
		int id = credencialesEntity.getScotiauser().getIdCliente();
		String url = apiUrl + "/v1/customer/{id}/getDespegarLink";
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(httpHeaders), String.class, id);
			return responseEntity.getBody();
		} catch(Exception e) {
			logger.error("Error obteniendo link de Despegar: " + e.getMessage());
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public CustomerRewardResponse realizarCanje(int idProducto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());
		Scotiauser scotiauser = credencialesEntity.getScotiauser();
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("id_producto", Integer.toString(idProducto));
		String nombreApellidoCliente = scotiauser.getFirstname() + "_" + scotiauser.getLastname();
		map.add("nombre_beneficiario", nombreApellidoCliente);
		map.add("rut_beneficiario", scotiauser.getRut());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,
				httpHeaders);

		int id = scotiauser.getIdCliente();
		String url = apiUrl + "/v1/customer/{id}/cupones/exchange";
		try {
			ResponseEntity<CustomerRewardResponse> exchangeResponseEntity = restTemplate.postForEntity(
					url, request, CustomerRewardResponse.class, id);
			return exchangeResponseEntity.getBody();
		} catch(Exception e) {
			logger.error("Error realizando canje de puntos: " + e.getMessage());
			CustomerRewardResponse customerRewardResponse = new CustomerRewardResponse();
			customerRewardResponse.setStatus("FAIL");
			customerRewardResponse.setMensaje("Ocurrió un error, no se pudo realizar el canje");
			return customerRewardResponse;
		}
	}
	
	public CustomerRewardResponse realizarCanjePorCatalogo(CanjeProducto canje) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());
		Scotiauser scotiauser = credencialesEntity.getScotiauser();
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("id_producto", Integer.toString(canje.getIdProducto()));
		String nombreApellidoCliente = scotiauser.getFirstname() + "_" + scotiauser.getLastname();
		map.add("nombre_beneficiario", nombreApellidoCliente);
		map.add("rut_beneficiario", scotiauser.getRut());
		String region = canje.getRegion();
		if(region == null) {
			map.add("id_region", "13");
		} else {
			map.add("id_region", canje.getRegion());
			map.add("comuna", canje.getComuna());
			map.add("direccion", canje.getDireccion());
			map.add("nro_calle", canje.getNroCalle());
			map.add("apartamento", canje.getApartamento());
			map.add("telefono", canje.getTelefono());
			map.add("correo", canje.getCorreo());
		}
		

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,
				httpHeaders);

		int id = scotiauser.getIdCliente();
		String url = apiUrl + "/v1/customer/{id}/cupones/catalog-exchange";
		try {
			ResponseEntity<CustomerRewardResponse> exchangeResponseEntity = restTemplate.postForEntity(
					url, request, CustomerRewardResponse.class, id);
			return exchangeResponseEntity.getBody();
		} catch(Exception e) {
			logger.error("Error realizando canje de puntos: " + e.getMessage());
			CustomerRewardResponse customerRewardResponse = new CustomerRewardResponse();
			customerRewardResponse.setStatus("FAIL");
			customerRewardResponse.setMensaje("Ocurrió un error, no se pudo realizar el canje");
			return customerRewardResponse;
		}
	}
	
	public CustomerRewardResponse realizarCanje(int idProducto, String nombreBeneficiario, String rutBeneficiario) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("id_producto", Integer.toString(idProducto));
		map.add("nombre_beneficiario", nombreBeneficiario.replace(" ", "_"));
		map.add("rut_beneficiario", rutBeneficiario);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,
				httpHeaders);

		int id = credencialesEntity.getScotiauser().getIdCliente();
		String url = apiUrl + "/v1/customer/{id}/cupones/exchange";
		try {
			ResponseEntity<CustomerRewardResponse> exchangeResponseEntity = restTemplate.postForEntity(
					url, request, CustomerRewardResponse.class, id);
			return exchangeResponseEntity.getBody();
		} catch(Exception e) {
			logger.error("Error realizando canje de puntos: " + e.getMessage());
			CustomerRewardResponse customerRewardResponse = new CustomerRewardResponse();
			customerRewardResponse.setStatus("FAIL");
			customerRewardResponse.setMensaje("Ocurrió un error, no se pudo realizar el canje");
			return customerRewardResponse;
		}
	}
	
	public CustomerRewardResponse realizarCanjeDirecto(int idProducto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("id_producto", Integer.toString(idProducto));

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,
				httpHeaders);

		int id = credencialesEntity.getScotiauser().getIdCliente();
		String url = apiUrl + "/v1/customer/{id}/cupones/exchangeDirectly";
		try {
			ResponseEntity<CustomerRewardResponse> exchangeDirectlyResponse = restTemplate.postForEntity(
					url, request, CustomerRewardResponse.class, id);
			return exchangeDirectlyResponse.getBody();
		} catch(Exception e) {
			logger.error("Error realizando canje directo: " + e.getMessage());
			CustomerRewardResponse customerRewardResponse = new CustomerRewardResponse();
			customerRewardResponse.setStatus("FAIL");
			customerRewardResponse.setMensaje("Ocurrió un error, no se pudo realizar el canje");
			return customerRewardResponse;
		}
	}

	public boolean inscribirPuntos(int idProducto, String cardKey, String cardNumber, int quantity) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("id_producto", Integer.toString(idProducto));
		map.add("card_key", cardKey);
		map.add("card_number", cardNumber);
		map.add("quantity", Integer.toString(quantity));

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,
				httpHeaders);

		int id = credencialesEntity.getScotiauser().getIdCliente();
		String url = apiUrl + "/v1/customer/{id}/points/inscribe";
		try {
			restTemplate.postForEntity(url, request, CustomerReward.class, id);
			return true;
		} catch(Exception e) {
			logger.error("Error inscribiendo puntos: " + e.getMessage());
			return false;
		}				
	}
	
	public CustomerRewardResponse realizarCanjeTipoRifa(int idRifa, int[] numeros) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("id_rifa", Integer.toString(idRifa));
		for (int numero : numeros) {
			map.add("numeros", numero + "");
		}		

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,
				httpHeaders);

		int id = credencialesEntity.getScotiauser().getIdCliente();
		String url = apiUrl + "/v1/customer/{id}/rifa/jugar";
		try {
			ResponseEntity<CustomerRewardResponse> exchangeResponseEntity = restTemplate.postForEntity(
					url, request, CustomerRewardResponse.class, id);
			return exchangeResponseEntity.getBody();
		} catch(Exception e) {
			logger.error("Error realizando canje de puntos por rifa: " + e.getMessage());
			CustomerRewardResponse customerRewardResponse = new CustomerRewardResponse();
			customerRewardResponse.setStatus("FAIL");
			customerRewardResponse.setMensaje("Ocurrió un error, no se pudo realizar el canje");
			return customerRewardResponse;
		}
	}
}
