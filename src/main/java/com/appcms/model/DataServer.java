package com.appcms.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.appcms.entity.Banner;
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
	@Cacheable(cacheNames="menu")
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

		ResponseEntity<Scinformacionsubmenu> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,Scinformacionsubmenu.class);

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
			System.out.println("toString: "+information.toString());
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

        System.out.println("requestxn: "+xresponse.getBody());   

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

        System.out.println("requestxn: "+xresponse.getBody());   

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

        System.out.println("requestxn: "+xresponse.getBody());   

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

        System.out.println("requestxn: "+xresponse.getBody());   

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

        System.out.println("requestxn: "+xresponse.getBody());   

		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			return null;
		}
		
	}
	
	
	public List<Banner> loadBannerAll() {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
//		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
		headers.set("Authorization", rqx.getSession().getAttribute("TOKENONE").toString());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = urlServer + "/cmsrest/get/bannerAll";

		ResponseEntity<List<Banner>> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<Banner>>() {
				});

        System.out.println("requestxn: "+xresponse.getBody());   

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

		String url = urlServer + "/cmsrest/get/informationScsubmenu/"+idsubmenu;

	
		ResponseEntity<Information> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,Information.class);

        System.out.println("requestxn: "+xresponse.getBody());   

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

		String url = urlServer + "/cmsrest/get/informationByName/"+idsubmenu;

	
		ResponseEntity<Information> xresponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity,Information.class);

        System.out.println("requestxn: "+xresponse.getBody());   

		if (xresponse.getStatusCodeValue() == 200) {
			return xresponse.getBody();
		} else {
			return null;
		}

	}
	

}
