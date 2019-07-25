package com.appcms.model;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
import com.appcms.entity.StockTicket;
import com.appcms.entity.Tarjetas;
import com.appcms.entity.UserCartola;
import com.appcms.entity.UserCartolaMovimiento;
import com.appcms.entity.UserCupon;
import com.appcms.entity.points.ExpiringPoints;
import com.appcms.entity.points.Points;
import com.appcms.security.RestAuthentication;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

@Component
public class DataServer {

	private String apiUrl;

	StringBuilder sb = new StringBuilder("");

	@Autowired
	public DataServer(@Qualifier("apiUrl") String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public Scmenu loadScmenuByName(HttpServletRequest rq, String scmenuName) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = apiUrl + "/get/scmenuByName/" + scmenuName;

		ResponseEntity<Scmenu> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<Scmenu>() {
				});
		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			return null;
		}

	}
	
	@Cacheable(cacheNames = "menu")
	public List<Scmenu> loadAllScmenu(HttpServletRequest rq) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = apiUrl + "/get/scmenu";

		ResponseEntity<List<Scmenu>> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<Scmenu>>() {
				});
		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			return null;
		}

	}

	public Scinformacionsubmenu loadInformatioSub(int idsubmenu, HttpServletRequest rq) {

		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);

		RestAuthentication xrestAuthentication = new RestAuthentication();
		System.out.println(xrestAuthentication.getTOKENONE() + " loadInformatioSub");
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = apiUrl + "/get/informationsubmenu/" + idsubmenu;

		ResponseEntity<Scinformacionsubmenu> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				Scinformacionsubmenu.class);

		if (xresponse.getStatusCodeValue() == 200) {

			Scinformacionsubmenu information = xresponse.getBody();
//			System.out.println("requestxn: "+information.getJson_condiciones());  
			String json = information.getJson_condiciones();
			JsonArray jsonObject = new JsonParser().parse(json).getAsJsonArray();

			JsonArray arr = jsonObject.getAsJsonArray();
			for (int i = 0; i < arr.size(); i++) {
				String post_id = arr.get(i).getAsString();
//				information.condicioneslista.add(post_id);
				information.addCondicioneslista(post_id);
			}
			System.out.println("toString: " + information.toString());
			return information;

		} else {
			return null;
		}

	}

	public List<ProductoTipoLike> loadProductosLike(int idsubmenu, HttpServletRequest rq) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = apiUrl + "/get/productosSubmenu/" + idsubmenu;

		ResponseEntity<List<ProductoTipoLike>> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<ProductoTipoLike>>() {
				});

		System.out.println("requestxn: " + xresponse.getBody());

		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			return null;
		}

	}

	public List<ProductoTipoLike> loadProductosLikeSubmenuCategoria(int idsubmenu, String categoria,
			HttpServletRequest rq) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = apiUrl + "/get/productosSubmenuCategoria/" + categoria + "/" + idsubmenu;

		ResponseEntity<List<ProductoTipoLike>> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<ProductoTipoLike>>() {
				});

		System.out.println("requestxn: " + xresponse.getBody());

		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			return null;
		}

	}

	public List<Scinformacionsubmenu> loadscmenuinformationFomScmenu(int idscbmenu, HttpServletRequest rq) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = apiUrl + "/get/informationsubmenuList/" + idscbmenu;

		ResponseEntity<List<Scinformacionsubmenu>> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<Scinformacionsubmenu>>() {
				});

		System.out.println("requestxn: " + xresponse.getBody());

		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			return null;
		}

	}

	public List<ProductoTipoLike> loadProductosDetalle(int idproducto, HttpServletRequest rq) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = apiUrl + "/get/detalleProducto/" + idproducto;

		ResponseEntity<ProductoTipoLike> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<ProductoTipoLike>() {
				});

		System.out.println("requestxn: " + xresponse.getBody());

		ProductoTipoLike producto = xresponse.getBody();
		List<FormatoDetalle> formatosDetalles = producto.getFormatosDetalles();
		
		ArrayList<FormatoDetalle> detalles = new ArrayList<>();
		ArrayList<FormatoDetalle> direcciones = new ArrayList<>();
		if (formatosDetalles != null) {
			for (int i = 0; i < formatosDetalles.size(); i++) {
				FormatoDetalle formatoDetalle = formatosDetalles.get(i);
				if (formatoDetalle.getTipo() == 1) { //Si es tipo 1 es detalle
					detalles.add(formatoDetalle);
				} else { // Si es tipo 2 (u otro) es direccion
					direcciones.add(formatoDetalle);
				}
			}
		}		
		producto.setDetalles(detalles);
		producto.setDirecciones(direcciones);
		if (xresponse.getStatusCodeValue() == 200) {
			ArrayList<ProductoTipoLike> productoList = new ArrayList<>();
			productoList.add(producto);
			return productoList;
		} else {
			return null;
		}

	}

	public List<ProductoCategoria> loadCateProductosFromCategoria(int idsubmenu, HttpServletRequest rq) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = apiUrl + "/get/productoCategoria/" + idsubmenu;

		ResponseEntity<List<ProductoCategoria>> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<ProductoCategoria>>() {
				});

		System.out.println("requestxn: " + xresponse.getBody());

		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			return null;
		}

	}

	public List<ProductoCategoria> loadproductoCategoriaConProductos(int idsubmenu, String categoria,
			HttpServletRequest rq) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = apiUrl + "/get/productoCategoriaConProductos/" + categoria + "/" + idsubmenu;
		System.out.println("URL para obtener categorias con productos: " + url);
		ResponseEntity<List<ProductoCategoria>> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<ProductoCategoria>>() {
				});

		System.out.println("requestxn: " + xresponse.getBody());

		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			return null;
		}

	}

	public List<Banner> loadBannerAll(int responsive, HttpServletRequest rq) {
		System.out.println(this.apiUrl);
		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = apiUrl + "/get/bannerAll/" + responsive;
		System.out.println("Banners: " + url);

		ResponseEntity<List<Banner>> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<Banner>>() {
				});

		System.out.println("requestxn: " + xresponse.getBody());

		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			return null;
		}

	}

	public Information loadInformationScsubmenu(int idsubmenu, HttpServletRequest rq) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = apiUrl + "/get/informationScsubmenu/" + idsubmenu;

		ResponseEntity<Information> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				Information.class);

		System.out.println("requestxn: " + xresponse.getBody());

		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			return null;
		}

	}

	public Information loadInformationByName(String idsubmenu, HttpServletRequest rq) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = apiUrl + "/get/informationByName/" + idsubmenu;

		ResponseEntity<Information> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				Information.class);

		System.out.println("requestxn: " + xresponse.getBody());

		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			return null;
		}

	}

	public String setReward(CustomerReward reward, String nombreTicket, String rut, HttpServletRequest rq) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());

		String url = apiUrl + "/customerreward/set";
		System.out.println("data param: " + reward.toString());

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
		System.out.println("pre: " + reward.toString());

		HttpEntity<?> httpEntity = new HttpEntity<Object>(xparam, headers);
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> xresponse = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
//		ResponseEntity<String> xresponse = this.restTemplate.exchange("http://142.93.62.102:9080/cmsrest/view/set", HttpMethod.POST, httpEntity, String.class);

		System.out.println("requestxn: " + xresponse.getBody());

		if (xresponse.getStatusCode() == HttpStatus.OK) {
			return xresponse.getBody();
//        	return true;
		} else {
			return null;
		}
//		if (xresponse.getStatusCodeValue() == 200) {
//			return xresponse.getBody();
//		} else {
//			return null;
//		}

	}

	public ProductoTipoLike loadProductoById(int idProd, HttpServletRequest rq) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//			System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = apiUrl + "/producto/get/" + idProd;

//			ResponseEntity<List<ProductoTipoLike>> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
//					new ParameterizedTypeReference<List<ProductoTipoLike>>() {
//					});
		ResponseEntity<ProductoTipoLike> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				ProductoTipoLike.class);
		System.out.println("requestxn: " + xresponse.getBody());

		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			ProductoTipoLike responsenull = new ProductoTipoLike();
			responsenull.setId(0);
			return responsenull;
		}

	}

	public String testLogin(String rut, String pass, HttpServletRequest rq) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());

		String url = apiUrl + "/v1/login_customer";

		MultiValueMap<String, String> xparam = new LinkedMultiValueMap<String, String>();
		xparam.add("userCostumer", rut);
		xparam.add("userPassword", pass);

		HttpEntity<?> httpEntity = new HttpEntity<Object>(xparam, headers);
		RestTemplate restTemplate = new RestTemplate();

		try {
			ResponseEntity<String> xresponse = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			System.out.println("requestxn: " + xresponse.getBody());

			if (xresponse.getStatusCode() == HttpStatus.OK) {
				System.out.println(xresponse.getHeaders());
				return xresponse.getHeaders().get("Authorization").toString().replace("Bearer ", "");
			} else {
				return null;
			}
		} catch (Exception ex) {
			System.out.println("error: " + ex.getMessage());
			return null;
		}

//		if (xresponse.getStatusCodeValue() == 200) {
//			return xresponse.getBody();
//		} else {
//			return null;
//		}

	}

	public UserCartola loadUserCartola() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CredencialesEntity credencialesEntity = (CredencialesEntity) auth.getPrincipal();
		Scotiauser scotiauser = credencialesEntity.getScotiauser();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "Bearer " + credencialesEntity.getTOKENONE());
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
					apiUrl + "/v1/customer/transactions?year=" + year, HttpMethod.GET, new HttpEntity<Object>(httpHeaders),
					new ParameterizedTypeReference<List<UserCartolaMovimiento>>() {
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

	public Points loadUserPoints() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CredencialesEntity credencialesEntity = (CredencialesEntity) auth.getPrincipal();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "Bearer " + credencialesEntity.getTOKENONE());
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getTOKENTWO());
		try {
			ResponseEntity<Points> pointsResponseEntity = restTemplate.exchange(apiUrl + "/v1/customer/points",
					HttpMethod.GET, new HttpEntity<Object>(httpHeaders), Points.class);
			Points points = pointsResponseEntity.getBody();
			return points;
		} catch (Exception e) {
			Points points = new Points();
			points.setAvailablePoints(-1);
			ExpiringPoints expiringPoints = new ExpiringPoints();
			expiringPoints.setPoints(-1);
			expiringPoints.setExpirationDate("N/A");
			points.setExpiringPoints(expiringPoints);
			return points;
		}
	}

	public List<UserCupon> loadCupones(int idUser, HttpServletRequest rq) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = apiUrl + "/get/usercupones/" + idUser;

		ResponseEntity<List<UserCupon>> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<UserCupon>>() {
				});

		System.out.println("requestxn: " + xresponse.getBody());

		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			return null;
		}

	}

	public byte[] loadCuponPdf(int idUser, int idReward, HttpServletRequest rq) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = apiUrl + "/get/usercuponjosticket/" + idUser + "/" + idReward;

		ResponseEntity<UserCupon> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity, UserCupon.class);

		System.out.println("requestxnfr: " + xresponse.getBody());

		if (xresponse.getStatusCodeValue() == 200) {
//			return xresponse.getBody();		CENCOSUD_TEST

			UserCupon cuponusr = new UserCupon();
			cuponusr = xresponse.getBody();

			URL urlTicketera;
			byte[] response = null;
			try {
				// http://ticket.clubadelante.cl/getPDHtml/CENCOSUD/000000/000000
				// http://ticket.clubadelante.cl/getPDF/:empresa/:codigo/:idcliente
//				urlTicketera = new URL("http://ticket.clubadelante.cl/getPDF/"+cuponusr.getNombre()+"/"+cuponusr.getId_cupon()+"/177824577");
//				urlTicketera = new URL("http://206.189.70.163/test/lorem-ipsum.pdf");

				System.out.println(apiUrl + "/get/getPDFile/" + cuponusr.getNombre() + "/" + cuponusr.getCodigo() + "/"
						+ cuponusr.getImagen());
				urlTicketera = new URL("http://ticket.clubadelante.cl/getPDFile/" + cuponusr.getNombre() + "/"
						+ cuponusr.getCodigo() + "/" + cuponusr.getImagen());
//				urlTicketera = new URL(urlServer + "/get/getPDFile/"+cuponusr.getNombre()+"/"+cuponusr.getCodigo()+"/"+cuponusr.getImagen());

				InputStream in = new BufferedInputStream(urlTicketera.openStream());
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] buf = new byte[2048];
				int n = 0;
				while (-1 != (n = in.read(buf))) {
					out.write(buf, 0, n);
				}
				out.close();
				in.close();
				response = out.toByteArray();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return response;

		} else {
			return null;
		}
	}

	public StockTicket loadStockTicket(String empresa, HttpServletRequest rq) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//			System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rq.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = apiUrl + "/get/stockticket/" + empresa;

//			ResponseEntity<List<ProductoTipoLike>> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
//					new ParameterizedTypeReference<List<ProductoTipoLike>>() {
//					});
		ResponseEntity<StockTicket> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				StockTicket.class);
		System.out.println("requestxnstock: " + xresponse.getBody().toString());

		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			System.out.println("no200xx");
			StockTicket responsenull = new StockTicket();
			return responsenull;
		}

	}
	
	public Tarjetas loadUserTarjetas() {
		//Se coloca el try acá arriba porque en caso de un usuario no autenticado, no se deben mostrar tarjetas
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			CredencialesEntity credencialesEntity = (CredencialesEntity) auth.getPrincipal();
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("AuthorizationCustomer", credencialesEntity.getTOKENTWO());
		
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

}
