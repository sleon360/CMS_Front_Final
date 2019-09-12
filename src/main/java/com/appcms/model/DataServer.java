package com.appcms.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
import com.appcms.entity.Banner;
import com.appcms.entity.CustomerInscripcion;
import com.appcms.entity.CustomerReward;
import com.appcms.entity.CustomerRewardResponse;
import com.appcms.entity.FormatoDetalle;
import com.appcms.entity.Information;
import com.appcms.entity.ProductoCategoria;
import com.appcms.entity.ProductoTipoLike;
import com.appcms.entity.Scinformacionsubmenu;
import com.appcms.entity.Scmenu;
import com.appcms.entity.Scotiauser;
import com.appcms.entity.StockTicket;
import com.appcms.entity.TagProducto;
import com.appcms.entity.Tarjetas;
import com.appcms.entity.UserCartola;
import com.appcms.entity.UserCartolaMovimiento;
import com.appcms.entity.UserCupon;
import com.appcms.entity.UserGusto;
import com.appcms.entity.customer.Customer;
import com.appcms.entity.points.ExpiringPoints;
import com.appcms.entity.points.Points;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

@Service
public class DataServer {

	private String apiUrl;	
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	public DataServer(@Qualifier("apiUrl") String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public Scmenu loadScmenuByName(String scmenuName) {
		String url = apiUrl + "/get/scmenuByName/" + scmenuName;
		try {
			return restTemplate.getForObject(url, Scmenu.class);
		} catch(Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	public List<Scmenu> loadAllScmenu() {
		String url = apiUrl + "/get/scmenu";
		try {
			ResponseEntity<List<Scmenu>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,
					new ParameterizedTypeReference<List<Scmenu>>() {
					});
			return responseEntity.getBody();
		} catch (Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public Scinformacionsubmenu loadInformatioSub(int idsubmenu) {
		String url = apiUrl + "/get/informationsubmenu/" + idsubmenu;
		try {
			Scinformacionsubmenu information = restTemplate.getForObject(url, Scinformacionsubmenu.class);
			String json = information.getJson_condiciones();
			JsonArray jsonObject = new JsonParser().parse(json).getAsJsonArray();
			JsonArray arr = jsonObject.getAsJsonArray();
			for (int i = 0; i < arr.size(); i++) {
				String post_id = arr.get(i).getAsString();
				information.addCondicioneslista(post_id);
			}
			System.out.println("Excepción no generada");
			return information;
		} catch(Exception e) {
			System.out.println("Excepción generada");
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public List<ProductoTipoLike> loadProductosLike(int idsubmenu) {
		String url = apiUrl + "/get/productosSubmenu/" + idsubmenu;
		try {
			ResponseEntity<List<ProductoTipoLike>> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,
					new ParameterizedTypeReference<List<ProductoTipoLike>>() {
					});
			return response.getBody();
		} catch(Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public List<ProductoTipoLike> loadProductosLikeSubmenuCategoria(int idsubmenu, String categoria) {
		String url = apiUrl + "/get/productosSubmenuCategoria/" + categoria + "/" + idsubmenu;
		try {
			ResponseEntity<List<ProductoTipoLike>> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,
					new ParameterizedTypeReference<List<ProductoTipoLike>>() {
					});
			return response.getBody();
		} catch(Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public List<Scinformacionsubmenu> loadscmenuinformationFomScmenu(int idscbmenu) {
		String url = apiUrl + "/get/informationsubmenuList/" + idscbmenu;
		try {
			ResponseEntity<List<Scinformacionsubmenu>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,
					new ParameterizedTypeReference<List<Scinformacionsubmenu>>() {
					});
			return responseEntity.getBody();
		} catch(Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public List<ProductoTipoLike> loadProductosDetalle(int idproducto) {
		String url = apiUrl + "/get/detalleProducto/" + idproducto;
		try {
			ResponseEntity<ProductoTipoLike> xresponse = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<ProductoTipoLike>() {});
			ProductoTipoLike producto = xresponse.getBody();
			List<FormatoDetalle> formatosDetalles = producto.getFormatosDetalles();

			ArrayList<FormatoDetalle> detalles = new ArrayList<>();
			ArrayList<FormatoDetalle> direcciones = new ArrayList<>();
			if (formatosDetalles != null) {
				for (int i = 0; i < formatosDetalles.size(); i++) {
					FormatoDetalle formatoDetalle = formatosDetalles.get(i);
					if (formatoDetalle.getTipo() == 1) { // Si es tipo 1 es detalle
						detalles.add(formatoDetalle);
					} else { // Si es tipo 2 (u otro) es direccion
						direcciones.add(formatoDetalle);
					}
				}
			}
			producto.setDetalles(detalles);
			producto.setDirecciones(direcciones);
			ArrayList<ProductoTipoLike> productoList = new ArrayList<>();
			productoList.add(producto);
			return productoList;
		} catch(Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public List<ProductoCategoria> loadCateProductosFromCategoria(int idsubmenu) {
		String url = apiUrl + "/get/productoCategoria/" + idsubmenu;
		try {
			ResponseEntity<List<ProductoCategoria>> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,new ParameterizedTypeReference<List<ProductoCategoria>>() {});
			return response.getBody();
		} catch(Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	public ProductoCategoria loadProductoCategoria(int idProducto) {
		String url = apiUrl + "/get/productos/" + idProducto + "/categoria";
		try {
			return restTemplate.getForObject(url, ProductoCategoria.class);
		} catch(Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public List<ProductoCategoria> loadproductoCategoriaConProductos(int idsubmenu, String categoria) {
		String url = apiUrl + "/get/productoCategoriaConProductos/" + categoria + "/" + idsubmenu;
		try {
			ResponseEntity<List<ProductoCategoria>> xresponse = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,
					new ParameterizedTypeReference<List<ProductoCategoria>>() {
					});
			return xresponse.getBody();
		} catch(Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public List<Banner> loadBannerAll(int responsive) {
		String url = apiUrl + "/get/bannerAll/" + responsive;
		try {
			ResponseEntity<List<Banner>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,
					new ParameterizedTypeReference<List<Banner>>() {
					});
			return responseEntity.getBody();
		} catch(Exception e) {
			return new ArrayList<>();
		}
	}

	public Information loadInformationScsubmenu(int idsubmenu) {
		String url = apiUrl + "/get/informationScsubmenu/" + idsubmenu;
		try {
			return restTemplate.getForObject(url, Information.class);
		} catch(Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public Information loadInformationByName(String idsubmenu) {
		String url = apiUrl + "/get/informationByName/" + idsubmenu;
		try {
			return restTemplate.getForObject(url, Information.class);
		} catch(Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ProductoTipoLike loadProductoById(int idProd) {
		String url = apiUrl + "/get/detalleProducto/" + idProd;
		try {
			return restTemplate.getForObject(url, ProductoTipoLike.class);
		} catch(Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	public UserCartola loadUserCartola() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		Scotiauser scotiauser = credencialesEntity.getScotiauser();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());

		/* SE RECUPERAN LOS PUNTOS DE CLIENTE */
		Points points = new Points();
		SimpleDateFormat formatter = new SimpleDateFormat("'al' dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
		Date date = new Date(System.currentTimeMillis());
		String fechaActual = formatter.format(date);
		try {
			ResponseEntity<Points> pointsResponseEntity = restTemplate.exchange(apiUrl + "/v1/customer/points",
					HttpMethod.GET, new HttpEntity<Object>(httpHeaders), Points.class);
			points = pointsResponseEntity.getBody();
		} catch (Exception e) {
			points.setAvailablePoints(0);
			ExpiringPoints expiringPoints = new ExpiringPoints();
			expiringPoints.setPoints(0);
			expiringPoints.setExpirationDate("N/A");
			points.setExpiringPoints(expiringPoints);
			points.setRegisteredPoints(0);
		}

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
			movimientos.clear();
		}
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

	public List<CustomerInscripcion> loadUserInscripciones() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());
		try {
			ResponseEntity<List<CustomerInscripcion>> inscripcionesResponseEntity = restTemplate.exchange(apiUrl + "/v1/customer/" + credencialesEntity.getScotiauser().getId_cliente() + "/inscripciones",
					HttpMethod.GET, new HttpEntity<Object>(httpHeaders), 
					new ParameterizedTypeReference<List<CustomerInscripcion>>() {
					});
			return inscripcionesResponseEntity.getBody();
		} catch (Exception e) {
			//Acá se debería loguear el error
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
			return pointsResponse.getBody();
		} catch (Exception e) {
			Points points = new Points();
			points.setAvailablePoints(0);
			ExpiringPoints expiringPoints = new ExpiringPoints();
			expiringPoints.setPoints(0);
			expiringPoints.setExpirationDate("N/A");
			points.setExpiringPoints(expiringPoints);
			points.setRegisteredPoints(0);
			return points;
		}
	}

	public List<UserCupon> loadCupones() {
		HttpHeaders headers = new HttpHeaders();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		headers.set("AuthorizationCustomer", credencialesEntity.getJwt());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		String url = apiUrl + "/v1/customer/" + credencialesEntity.getScotiauser().getId_cliente() + "/cupones" ;
		try {
			ResponseEntity<List<UserCupon>> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
					new ParameterizedTypeReference<List<UserCupon>>() {
					});
			return xresponse.getBody();
		} catch (Exception e) {
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

	public StockTicket loadStockTicket(String empresa) {
		String url = apiUrl + "/get/stockticket/" + empresa;
		try {
			return restTemplate.getForObject(url, StockTicket.class);
		} catch(Exception e) {
			return new StockTicket();
		}
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
			Tarjetas tarjetas = new Tarjetas();
			tarjetas.setTarjetasCliente(new ArrayList<>());
			tarjetas.setTipoCliente("NORMAL");
			return tarjetas;
		}
	}

	public List<UserGusto> loadGustos() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());
		try {
			ResponseEntity<List<UserGusto>> gustosResponseEntity = restTemplate.exchange(
					apiUrl + "/get/customer_gustos", HttpMethod.GET, new HttpEntity<Object>(httpHeaders),
					new ParameterizedTypeReference<List<UserGusto>>() {
					});
			return gustosResponseEntity.getBody();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public List<UserGusto> loadCustomerGustos() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());
		int id = credencialesEntity.getScotiauser().getId_cliente();
		try {
			ResponseEntity<List<UserGusto>> response = restTemplate.exchange(apiUrl + "/v1/customer/" + id + "/customer_gustos", HttpMethod.GET, new HttpEntity<Object>(httpHeaders), new ParameterizedTypeReference<List<UserGusto>>() {});
			return response.getBody();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public void saveCustomerGustos(String[] gustos) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());
		int id = credencialesEntity.getScotiauser().getId_cliente();

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
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public List<TagProducto> loadTagsProductos() {
		String url = apiUrl + "/get/tags_productos/getTop10";
		try {
			ResponseEntity<List<TagProducto>> tagsProductosResponseEntity = restTemplate.exchange(
					url, HttpMethod.GET, HttpEntity.EMPTY,
					new ParameterizedTypeReference<List<TagProducto>>() {
					});
			return tagsProductosResponseEntity.getBody();
		} catch (Exception e) {
			return new ArrayList<>();
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
		map.add("nombre_beneficiario", scotiauser.getFirstname());
		map.add("rut_beneficiario", scotiauser.getRut());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,
				httpHeaders);

		int id = scotiauser.getId_cliente();
		String url = apiUrl + "/v1/customer/{id}/cupones/exchange";
		ResponseEntity<CustomerRewardResponse> tagsProductosResponseEntity = restTemplate.postForEntity(
				url, request, CustomerRewardResponse.class, id);
		return tagsProductosResponseEntity.getBody();
	}
	
	public CustomerRewardResponse realizarCanje(int idProducto, String nombreBeneficiario, String rutBeneficiario) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("id_producto", Integer.toString(idProducto));
		map.add("nombre_beneficiario", nombreBeneficiario);
		map.add("rut_beneficiario", rutBeneficiario);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,
				httpHeaders);

		int id = credencialesEntity.getScotiauser().getId_cliente();
		String url = apiUrl + "/v1/customer/{id}/cupones/exchange";
		ResponseEntity<CustomerRewardResponse> tagsProductosResponseEntity = restTemplate.postForEntity(
				url, request, CustomerRewardResponse.class, id);
		return tagsProductosResponseEntity.getBody();
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

		int id = credencialesEntity.getScotiauser().getId_cliente();
		String url = apiUrl + "/v1/customer/{id}/cupones/exchangeDirectly";
		ResponseEntity<CustomerRewardResponse> exchangeDirectlyResponse = restTemplate.postForEntity(
				url, request, CustomerRewardResponse.class, id);
		return exchangeDirectlyResponse.getBody();
	}

	public boolean inscribirPuntos(int idProducto, String cardKey, int quantity) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("id_producto", Integer.toString(idProducto));
		map.add("card_key", cardKey);
		map.add("quantity", Integer.toString(quantity));

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,
				httpHeaders);

		int id = credencialesEntity.getScotiauser().getId_cliente();
		String url = apiUrl + "/v1/customer/{id}/points/inscribe";
		try {
			restTemplate.postForEntity(url, request, CustomerReward.class, id);
			return true;
		} catch(Exception e) {
			return false;
		}			
	}

	public String getDespegarLink() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer credencialesEntity = (Customer) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getJwt());
		int id = credencialesEntity.getScotiauser().getId_cliente();
		String url = apiUrl + "/v1/customer/{id}/getDespegarLink";
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(httpHeaders), String.class, id);
			return responseEntity.getBody();
		} catch(Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
