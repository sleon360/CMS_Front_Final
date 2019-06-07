package com.appcms.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.appcms.entity.Banner;
import com.appcms.entity.CustomerReward;
import com.appcms.entity.Information;
import com.appcms.entity.ProductoCategoria;
import com.appcms.entity.ProductoTipoLike;
import com.appcms.entity.Scinformacionsubmenu;
import com.appcms.entity.Scmenu;
import com.appcms.security.RestAuthentication;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class DataServer {

	private final String urlServer = "http://localhost:9080";

	StringBuilder sb = new StringBuilder("");
	private HttpServletRequest rqx;

	public DataServer(HttpServletRequest rq) {
		rqx = rq;
	}

	@Cacheable(cacheNames = "menu")
	public List<Scmenu> loadScmenu() {

		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rqx.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = urlServer + "/cmsrest/get/scmenu";

		ResponseEntity<List<Scmenu>> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<Scmenu>>() {
				});

//        System.out.println("requestxn: "+xresponse.getBody());   

		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			return null;
		}

	}

	public Scinformacionsubmenu loadInformatioSub(int idsubmenu) {

		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);

		RestAuthentication xrestAuthentication = new RestAuthentication();
		System.out.println(xrestAuthentication.getTOKENONE() + " loadInformatioSub");
		headers.set("Authorization", rqx.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = urlServer + "/cmsrest/get/informationsubmenu/" + idsubmenu;

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

	public List<ProductoTipoLike> loadProductosLike(int idsubmenu) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rqx.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = urlServer + "/cmsrest/get/productosSubmenu/" + idsubmenu;

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

	public List<ProductoTipoLike> loadProductosLikeSubmenuCategoria(int idsubmenu, String categoria) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rqx.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = urlServer + "/cmsrest/get/productosSubmenuCategoria/" + categoria + "/" + idsubmenu;

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

	public List<ProductoTipoLike> loadProductosDetalle(int idproducto) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rqx.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = urlServer + "/cmsrest/get/detalleProducto/" + idproducto;

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

	public List<ProductoCategoria> loadCateProductosFromCategoria(int idsubmenu) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rqx.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = urlServer + "/cmsrest/get/productoCategoria/" + idsubmenu;

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

	public List<ProductoCategoria> loadproductoCategoriaConProductos(int idsubmenu, String categoria) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rqx.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = urlServer + "/cmsrest/get/productoCategoriaConProductos/" + categoria + "/" + idsubmenu;

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

	public List<Banner> loadBannerAll(int responsive) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rqx.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = urlServer + "/cmsrest/get/bannerAll/"+responsive;
		System.out.println(url);

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

	
	
	public Information loadInformationScsubmenu(int idsubmenu) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rqx.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = urlServer + "/cmsrest/get/informationScsubmenu/" + idsubmenu;

		ResponseEntity<Information> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				Information.class);

		System.out.println("requestxn: " + xresponse.getBody());

		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			return null;
		}

	}

	public Information loadInformationByName(String idsubmenu) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rqx.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = urlServer + "/cmsrest/get/informationByName/" + idsubmenu;

		ResponseEntity<Information> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				Information.class);

		System.out.println("requestxn: " + xresponse.getBody());

		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			return null;
		}

	}

	public String setReward(CustomerReward reward) {
		System.out.println("Cambiando puntos");
		
		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rqx.getSession().getAttribute("TOKENONE").toString());

		String url = urlServer + "/cmsrest/customerreward/set";
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

	public ProductoTipoLike loadProductoById(int idProd) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//			System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rqx.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = urlServer + "/cmsrest/get/ProductoById/" + idProd;

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
	
	
	
	
	public String testLogin(String rut, String pass) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rqx.getSession().getAttribute("TOKENONE").toString());

		String url = urlServer + "/v1/login_customer";

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
	
	
	
	

}
