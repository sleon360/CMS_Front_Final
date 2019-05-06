package com.appcms.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
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
		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
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
	
	public List<ProductoTipoLike> loadProductosDetalle(int idproducto) {

		HttpHeaders headers = new HttpHeaders();

		RestAuthentication xrestAuthentication = new RestAuthentication();
		System.out.println(xrestAuthentication.getTOKENONE() + " 666666666666666666666666666666666666666xn");
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
	
	public static List<ProductoCategoria> loadCateProductosFromCategoria(String strIndexCategoria) {
		 List<ProductoCategoria> catelist =  new ArrayList<>();
		 catelist = Emudata.getCategoriasProductosTest();
		 
		 List<ProductoCategoria> catelistResult =  new ArrayList<>();
		 
		 for (ProductoCategoria catesel : catelist) //buscamos el menu que seleccionó
			{ 
				if(catesel.getStrIndex().equalsIgnoreCase(strIndexCategoria) ) {			
					
					catesel.productosList = Emudata.getProductoseEcomerceTest();
					
					catelistResult.add(catesel); // se encuentra la categoria y se inserta					
					
					break;
				}
			}
		 
		 return catelistResult;
	}

}
