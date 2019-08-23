package com.appcms.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.appcms.entity.Banner;
import com.appcms.entity.CredencialesEntity;
import com.appcms.entity.CustomerReward;
import com.appcms.entity.FormatoDetalle;
import com.appcms.entity.Information;
import com.appcms.entity.ProductoCategoria;
import com.appcms.entity.ProductoTipoLike;
import com.appcms.entity.Scinformacionsubmenu;
import com.appcms.entity.Scmenu;
import com.appcms.entity.Scotiauser;
import com.appcms.entity.Scsubmenu;
import com.appcms.entity.StockTicket;
import com.appcms.entity.TagProducto;
import com.appcms.entity.Tarjetas;
import com.appcms.entity.UserCartola;
import com.appcms.entity.UserCartolaMovimiento;
import com.appcms.entity.UserCupon;
import com.appcms.entity.UserGusto;
import com.appcms.entity.points.ExpiringPoints;
import com.appcms.entity.points.Points;
import com.cms.views.ViewApp;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

@Component
public class DataServer {

	private String apiUrl;
	private String TOKENONE;
	
	
	private RestTemplate restTemplate = new RestTemplate();
	
	StringBuilder sb = new StringBuilder("");

	public DataServer(@Qualifier("apiUrl") String xapiUrl,@Qualifier("TOKENONE") String xTOKENONE) {
		this.apiUrl = xapiUrl;
		this.TOKENONE = xTOKENONE;
		System.out.println("AAAAAAAASSSSSSSSSSSSDDDDDDDDDDDFFFFFFFF:"+xTOKENONE);
	}
	
	public void setRestemplate(RestTemplate xrestTemplate)
	{
		this.restTemplate=xrestTemplate;
	}

	public String getApiUrl()
	{
		return this.apiUrl;
	}
	
	public String getToken()
	{
		return this.TOKENONE;
	}

	
	public ResponseEntity<Scmenu> loadScmenuByName( String scmenuName) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", TOKENONE);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		
		String url = apiUrl + "/get/scmenuByName/" + scmenuName;
		 ResponseEntity<Scmenu> retorno=restTemplate.exchange(url, HttpMethod.GET, httpEntity,Scmenu.class);
		return retorno;
	}

	public ResponseEntity<List<Scmenu>> loadAllScmenu() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", TOKENONE);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		String url = apiUrl + "/get/scmenu";	
		ResponseEntity<List<Scmenu>> retorno=restTemplate.exchange(url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Scmenu>>() {});
		return retorno;
	}

	public ResponseEntity<Scinformacionsubmenu> loadInformatioSub(int idsubmenu) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", TOKENONE);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		String url = apiUrl + "/get/informationsubmenu/" + idsubmenu;
			ResponseEntity<Scinformacionsubmenu> information = restTemplate.exchange(url, HttpMethod.GET, httpEntity,Scinformacionsubmenu.class); 
			String json = information.getBody().getJson_condiciones();
			JsonArray jsonObject = new JsonParser().parse(json).getAsJsonArray();

			JsonArray arr = jsonObject.getAsJsonArray();
			for (int i = 0; i < arr.size(); i++) {
				String post_id = arr.get(i).getAsString();
				information.getBody().addCondicioneslista(post_id);
			}
			return information;

	}

	public ResponseEntity<List<ProductoTipoLike>> loadProductosLike(int idsubmenu) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", TOKENONE);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		String url = apiUrl + "/get/productosSubmenu/" + idsubmenu;
		ResponseEntity<List<ProductoTipoLike>> retorno=restTemplate.exchange(url, HttpMethod.GET, httpEntity,new ParameterizedTypeReference<List<ProductoTipoLike>>(){});
		return retorno;

	}

	public ResponseEntity<List<ProductoTipoLike>> loadProductosLikeSubmenuCategoria(int idsubmenu, String categoria) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", TOKENONE);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);

		String url = apiUrl + "/get/productosSubmenuCategoria/" + categoria + "/" + idsubmenu;
		return restTemplate.exchange(url, HttpMethod.GET, httpEntity,new ParameterizedTypeReference<List<ProductoTipoLike>>() {});
	}

	public ResponseEntity<List<Scinformacionsubmenu>> loadscmenuinformationFomScmenu(int idscbmenu) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", TOKENONE);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		String url = apiUrl + "/get/informationsubmenuList/" + idscbmenu;
		return restTemplate.exchange(url, HttpMethod.GET, httpEntity,new ParameterizedTypeReference<List<Scinformacionsubmenu>>() {});
	}

	public List<ProductoTipoLike> loadProductosDetalle(int idproducto) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", TOKENONE);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);

		String url = apiUrl + "/get/detalleProducto/" + idproducto;

		ResponseEntity<ProductoTipoLike> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,new ParameterizedTypeReference<ProductoTipoLike>() {});
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

	}

	public ResponseEntity<List<ProductoCategoria>> loadCateProductosFromCategoria(int idsubmenu) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", TOKENONE);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		String url = apiUrl + "/get/productoCategoria/" + idsubmenu;
		return restTemplate.exchange(url, HttpMethod.GET, httpEntity,new ParameterizedTypeReference<List<ProductoCategoria>>() {});
	}

	public ResponseEntity<List<ProductoCategoria>> loadproductoCategoriaConProductos(int idsubmenu, String categoria) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", TOKENONE);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);

		String url = apiUrl + "/get/productoCategoriaConProductos/" + categoria + "/" + idsubmenu;
		System.out.println("URL para obtener categorias con productos: " + url);
		ResponseEntity<List<ProductoCategoria>> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<ProductoCategoria>>() {
				});
			return xresponse;

	}

	public ResponseEntity<List<Banner>> loadBannerAll(int responsive) {
		System.out.println(this.apiUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", TOKENONE);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);

		String url = apiUrl + "/get/bannerAll/" + responsive;
		System.out.println("Banners: " + url);

		ResponseEntity<List<Banner>> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<Banner>>() {
				});

			return xresponse;
	}

	public ResponseEntity<Information> loadInformationScsubmenu(int idsubmenu) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", TOKENONE);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);;

		String url = apiUrl + "/get/informationScsubmenu/" + idsubmenu;

		return restTemplate.exchange(url, HttpMethod.GET, httpEntity,Information.class);

	}

	public ResponseEntity<Information> loadInformationByName(String idsubmenu) {
		HttpHeaders headers = new HttpHeaders();

		headers.set("Authorization",TOKENONE);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);	
		String url = apiUrl + "/get/informationByName/" + idsubmenu;
		return restTemplate.exchange(url, HttpMethod.GET, httpEntity,Information.class);



	}

	public ResponseEntity<String> setReward(CustomerReward reward, String nombreTicket, String rut) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", TOKENONE);
		String url = apiUrl + "/customerreward/set";
		MultiValueMap<String, String> xparam = new LinkedMultiValueMap<String, String>();
		xparam.add("customer_id", Integer.toString(reward.getCustomer_id()));
		xparam.add("order_id", Integer.toString(reward.getOrder_id()));
		xparam.add("description", reward.getDescription());
		xparam.add("points", Integer.toString(reward.getPoints()));
		xparam.add("date_added", reward.getDate_added());
		xparam.add("date_vencimiento", reward.getDate_vencimiento());
		xparam.add("id_campana", Integer.toString(reward.getId_campana()));
		xparam.add("id_trx", Integer.toString(reward.getId_trx()));
		xparam.add("id_jos_ticket", Integer.toString(reward.getId_jos_ticket()));
		xparam.add("tipo_reward", Integer.toString(reward.getTipo_reward()));
		xparam.add("nombre_ticket", nombreTicket);
		xparam.add("rut_cliente", rut);
		
		HttpEntity<?> httpEntity = new HttpEntity<Object>(xparam, headers);
		return restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
	}

	public ResponseEntity<ProductoTipoLike> loadProductoById(int idProd) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", TOKENONE);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		String url = apiUrl + "/get/detalleProducto/" + idProd;
		return restTemplate.exchange(url, HttpMethod.GET, httpEntity,ProductoTipoLike.class);
	}

	public ResponseEntity<String> testLogin(String rut, String pass) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", TOKENONE);

		String url = apiUrl + "/v1/login_customer";

		MultiValueMap<String, String> xparam = new LinkedMultiValueMap<String, String>();
		xparam.add("userCostumer", rut);
		xparam.add("userPassword", pass);

		HttpEntity<?> httpEntity = new HttpEntity<Object>(xparam, headers);
		return restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

	}

	public ResponseEntity<UserCartola> loadUserCartola() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CredencialesEntity credencialesEntity = (CredencialesEntity) auth.getPrincipal();
		Scotiauser scotiauser = credencialesEntity.getScotiauser();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "Bearer " + TOKENONE);
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getTOKENTWO());

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
			System.out.println(e.getMessage());
			points.setAvailablePoints(-1);
			ExpiringPoints expiringPoints = new ExpiringPoints();
			expiringPoints.setPoints(-1);
			expiringPoints.setExpirationDate("N/A");
			points.setExpiringPoints(expiringPoints);
			points.setRegisteredPoints(-1);
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
		
		
		return new ResponseEntity<UserCartola>(miCartola,HttpStatus.OK);
	}

	public ResponseEntity<Points> loadUserPoints() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CredencialesEntity credencialesEntity = (CredencialesEntity) auth.getPrincipal();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "Bearer " + TOKENONE);
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getTOKENTWO());
		try {
			return restTemplate.exchange(apiUrl + "/v1/customer/points",HttpMethod.GET, new HttpEntity<Object>(httpHeaders), Points.class);
		} catch (RestClientException e) {
			Points points = new Points();
			points.setAvailablePoints(-1);
			ExpiringPoints expiringPoints = new ExpiringPoints();
			expiringPoints.setPoints(-1);
			expiringPoints.setExpirationDate("N/A");
			points.setExpiringPoints(expiringPoints);
			return new ResponseEntity<Points>(points,HttpStatus.OK);
		}
	}

	public ResponseEntity<List<UserCupon>> loadCupones(int idUser) {

		HttpHeaders headers = new HttpHeaders();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CredencialesEntity credencialesEntity = (CredencialesEntity) auth.getPrincipal();
		headers.set("Authorization", "Bearer " + TOKENONE);
		headers.set("AuthorizationCustomer", credencialesEntity.getTOKENTWO());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		String url = apiUrl + "/v1/customer/" + idUser + "/cupones";
		return restTemplate.exchange(url, HttpMethod.GET, httpEntity,new ParameterizedTypeReference<List<UserCupon>>() {});

	}

	public ResponseEntity<byte[]> loadCuponAsPdf(int idUser, int idReward) {

		HttpHeaders headers = new HttpHeaders();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CredencialesEntity credencialesEntity = (CredencialesEntity) auth.getPrincipal();
		headers.set("Authorization", "Bearer " + TOKENONE);
		headers.set("AuthorizationCustomer", credencialesEntity.getTOKENTWO());

		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);

		String url = apiUrl + "/v1/customer/" + idUser + "/cupones/" + idReward + "/getAsPdf";

		//ResponseEntity<byte[]> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity, byte[].class);
		return restTemplate.exchange(url, HttpMethod.GET, httpEntity, byte[].class);
	}

	public ResponseEntity<StockTicket> loadStockTicket(String empresa) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", TOKENONE);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);

		String url = apiUrl + "/get/stockticket/" + empresa;

		return restTemplate.exchange(url, HttpMethod.GET, httpEntity,StockTicket.class);


	}

	public Tarjetas loadUserTarjetas() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			CredencialesEntity credencialesEntity = (CredencialesEntity) auth.getPrincipal();

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Authorization", TOKENONE);
			httpHeaders.set("AuthorizationCustomer", credencialesEntity.getTOKENTWO());
			ResponseEntity<Tarjetas> tarjetasResponseEntity = restTemplate.exchange(apiUrl + "/v1/customer/cards",HttpMethod.GET, new HttpEntity<Object>(httpHeaders), Tarjetas.class);
			return tarjetasResponseEntity.getBody();
		} catch (Exception e) {
			Tarjetas tarjetas = new Tarjetas();
			tarjetas.setTarjetasCliente(new ArrayList<>());
			tarjetas.setTipoCliente("NORMAL");
			return tarjetas;
		}
	}

	public ResponseEntity<List<UserGusto>> loadGustos() {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", TOKENONE);

			return restTemplate.exchange(apiUrl + "/active_customer_gustos", HttpMethod.GET, new HttpEntity<Object>(httpHeaders),new ParameterizedTypeReference<List<UserGusto>>() {});

	}

	public ResponseEntity<List<UserGusto>> loadCustomerGustos() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CredencialesEntity credencialesEntity = (CredencialesEntity) auth.getPrincipal();
	
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", TOKENONE);
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getTOKENTWO());
		int id = credencialesEntity.getScotiauser().getId_cliente();
		return restTemplate.exchange(apiUrl + "/v1/customer/" + id + "/customer_gustos", HttpMethod.GET,new HttpEntity<Object>(httpHeaders), new ParameterizedTypeReference<List<UserGusto>>() {});
	}

	public ResponseEntity<String> saveCustomerGustos(String[] gustos) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CredencialesEntity credencialesEntity = (CredencialesEntity) auth.getPrincipal();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", TOKENONE);
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getTOKENTWO());
		int id = credencialesEntity.getScotiauser().getId_cliente();

		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		for (int i = 0; i < gustos.length; i++) {
			map.add("gusto", gustos[i]);
		}
		System.out.println("Consultando al back");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,httpHeaders);
		return restTemplate.postForEntity(apiUrl + "/v1/customer/" + id + "/customer_gustos/save", request, String.class);
		
	}

	public ResponseEntity<List<TagProducto>> loadTagsProductos() {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", TOKENONE);
		return restTemplate.exchange(apiUrl + "/tags_productos/getTop10", HttpMethod.GET, new HttpEntity<Object>(httpHeaders),new ParameterizedTypeReference<List<TagProducto>>() {});

	}

}
