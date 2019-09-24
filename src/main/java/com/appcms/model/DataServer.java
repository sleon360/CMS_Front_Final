package com.appcms.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.appcms.entity.Banner;
import com.appcms.entity.Information;
import com.appcms.entity.ProductoCategoria;
import com.appcms.entity.ProductoTipoLike;
import com.appcms.entity.Scinformacionsubmenu;
import com.appcms.entity.Scmenu;
import com.appcms.entity.StockTicket;
import com.appcms.entity.TagProducto;
import com.appcms.entity.UserGusto;
import com.appcms.entity.customer.Customer;

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
			return information;
		} catch(Exception e) {
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
	
	public List<ProductoCategoria> loadScsubmenuRubros(int idsubmenu) {
		String url = apiUrl + "/get/scmenuSubmenu/" + idsubmenu + "/rubros";
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

	public StockTicket loadStockTicket(String empresa) {
		String url = apiUrl + "/get/stockticket/" + empresa;
		try {
			return restTemplate.getForObject(url, StockTicket.class);
		} catch(Exception e) {
			return new StockTicket();
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

}
