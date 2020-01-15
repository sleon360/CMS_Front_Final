package com.appcms.router;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import com.appcms.entity.CanjeProducto;
import com.appcms.entity.Information;
import com.appcms.entity.Scmenu;
import com.appcms.entity.Scsubmenu;
import com.appcms.entity.UserCupon;
import com.appcms.entity.UserGusto;
import com.appcms.entity.customer.Customer;
import com.appcms.model.DataServer;
import com.appcms.services.CustomerService;
import com.cms.errors.ViewRendererException;
import com.cms.views.ViewApp;

@Controller
public class Routes {
	
	@Autowired
	private ViewApp viewApp;

	@Autowired
	private DataServer dtserver;

	@Autowired
	private CustomerService customerModel;

	@Autowired
	private CategoryHeaderSetter categoryHeaderSetter;

	@GetMapping("/")
	public ModelAndView home(HttpServletRequest rq) throws ViewRendererException {
		ModelAndView mav = new ModelAndView(viewApp.loadViews("head", "index", "footer"));
		mav.addObject("banners", dtserver.loadBannerAll(0));
		mav.addObject("banners_resp", dtserver.loadBannerAll(1));
		mav.addObject("descuentos_destacados", dtserver.loadscmenuinformationFomScmenu(10));
		categoryHeaderSetter.setHeaders(mav);
		return mav;
	}

	@GetMapping("/categoria/{menu}/{submenu}/productos/{categoria}")
	public ModelAndView menuProductoCategoria(@PathVariable("menu") String menu,
			@PathVariable("submenu") String submenu, @PathVariable("categoria") String categoria,
			@RequestHeader(value = "referer", required = false) final String referer) throws ViewRendererException {
		ModelAndView mav = new ModelAndView(viewApp.loadViews("head", "HEADER_CATEGORIAS", "CATEGORIAS", "footer"));
		Scmenu scmenu = dtserver.loadScmenuByName(menu);
		Scsubmenu scmenuurlsub = new Scsubmenu();
		try {
			if (scmenu != null) {
				for (Scsubmenu scmenuurlsubtemp : scmenu.getSubmenues()) // buscamos el submenu que seleccionó
				{
					if (scmenuurlsubtemp.getStrIndex().equalsIgnoreCase(submenu)) {
						scmenuurlsub = scmenuurlsubtemp;
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		if (scmenuurlsub.getId() == 0) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}

		switch (scmenuurlsub.getTipo()) { // SI O SI TIENE QUE SER SUB CATEGORIA TIPO 5 o 6 PARA TENER PRODUCTOS A LA
											// CATEGORIAPRODUCTOS ASOCIADA
		case 5:
			// TIPO CANJE CON CATEGORIAS
			scmenuurlsub.setCategoriaProductoLista(
					dtserver.loadproductoCategoriaConProductos(scmenuurlsub.getId(), categoria));
			mav.addObject("verProductosCategoria", true);
			break;

		case 6:
			// TIPO INSCRIPCIÓN DE PUNTOS
			// se pasa la categoria para seleccionar el primer producto de ella, deberia
			// siempre tener 1 producto MAXIMO por categoria tipo formulario

			// Si el usuario no se ha autenticado, no debería avanzarse ya que se requieren
			// los puntos y las tarjetas del cliente
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
			if (resolver.isAnonymous(auth)) {
				// Para evitar que una página quede como ?login?login se hace el replace
				return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
			}

			scmenuurlsub
					.setProductosLikeLista(dtserver.loadProductosLikeSubmenuCategoria(scmenuurlsub.getId(), categoria));
			mav.addObject("producto", new CanjeProducto());
			mav.addObject("verProductosCategoria", true);

			// Se agregan los puntos de cliente
			mav.addObject("puntosDisponibles", customerModel.loadUserPoints().getAvailablePoints());
			// Se agregan las tarjetas del cliente
			scmenuurlsub.setTarjetasCliente(customerModel.loadUserTarjetas().getTarjetasCliente());
			break;
		case 8:
			// TIPO TIPO CANJE DESCUENTOS
			scmenuurlsub.setCategoriaProductoLista(
					dtserver.loadproductoCategoriaConProductos(scmenuurlsub.getId(), categoria));
			mav.addObject("verProductosCategoria", true);
			break;
		default:
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);
		categoryHeaderSetter.setHeaders(mav);

		return mav;
	}

	@GetMapping("/user/{menu}/{submenu}")
	public ModelAndView menuUser(@PathVariable("menu") String menu, @PathVariable("submenu") String submenu,
			@RequestHeader(value = "referer", required = false) final String referer) throws ViewRendererException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (resolver.isAnonymous(auth)) {
			return new ModelAndView("redirect:/?login");
		}
		String html = viewApp.loadViews("HEAD", "HEADER_CATEGORIAS");
		Scmenu scmenu = dtserver.loadScmenuByName(menu);
		Scsubmenu scmenuurlsub = new Scsubmenu();

		ModelAndView mav = null;
		try {
			if (scmenu != null) {
				for (Scsubmenu scmenuurlsubtemp : scmenu.getSubmenues()) // buscamos el submenu que seleccionó
				{
					if (scmenuurlsubtemp.getStrIndex().equalsIgnoreCase(submenu)) {
						scmenuurlsub = scmenuurlsubtemp;
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		if (scmenuurlsub.getId() == 0) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}

		switch (scmenuurlsub.getTipo()) {
		case 20: // information
			// TIPO MI CARTOLA
			html += viewApp.loadViews("MI-CARTOLA", "FOOTER");
			mav = new ModelAndView(html);
			mav.addObject("cartola", customerModel.loadUserCartola());
			break;
		case 21: // information
			// TIPO INSCRIPCCION
			html += viewApp.loadViews("mis-inscripciones", "FOOTER");
			mav = new ModelAndView(html);
			mav.addObject("inscripciones", customerModel.loadUserInscripciones());
			break;
		case 22: // information
			// TIPO MIS CUPONES
			html += viewApp.loadViews("mis-cupones", "FOOTER");
			mav = new ModelAndView(html);
			List<UserCupon> cupones = customerModel.loadCupones();
			List<UserCupon> giftCards = new ArrayList<UserCupon>();
			List<UserCupon> entradasCine = new ArrayList<UserCupon>();
			List<UserCupon> panoramas = new ArrayList<UserCupon>();
			List<UserCupon> rifas = new ArrayList<UserCupon>();
			SimpleDateFormat currentDateFormatCupones = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat newDateFormatCupones = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < cupones.size(); i++) {
				UserCupon cupon = cupones.get(i);
				String fechaEmitido = cupon.getFecha_emitido();
				String fechaVencimiento = cupon.getFecha_vencimiento();
				try {
					Date dateEmitido = currentDateFormatCupones.parse(fechaEmitido);
					cupon.setFecha_emitido(newDateFormatCupones.format(dateEmitido));
				} catch (Exception e) {
					cupon.setFecha_emitido("N/A");
				}
				try {
					Date dateVencimiento = currentDateFormatCupones.parse(fechaVencimiento);
					cupon.setFecha_vencimiento(newDateFormatCupones.format(dateVencimiento));
				} catch (Exception e) {
					cupon.setFecha_vencimiento("N/A");
				}
				switch (cupon.getTipoCupon()) {
				case 2:
					giftCards.add(cupon);
					break;
				case 3:
					entradasCine.add(cupon);
					break;
				case 4:
					panoramas.add(cupon);
					break;
				case 5:
					rifas.add(cupon);
					break;
				default:
					break;
				}
			}
			mav.addObject("giftCards", giftCards);
			mav.addObject("entradasCine", entradasCine);
			mav.addObject("panoramas", panoramas);
			mav.addObject("rifas", rifas);
			break;
		case 23:
			// TIPO MIS GUSTOS
			html += viewApp.loadViews("mis-preferencias", "FOOTER");
			mav = new ModelAndView(html);
			List<UserGusto> gustos = dtserver.loadGustos();
			List<UserGusto> gustosCliente = customerModel.loadCustomerGustos();
			for (int i = 0; i < gustos.size(); i++) {
				UserGusto gusto = gustos.get(i);
				for (int j = 0; j < gustosCliente.size(); j++) {
					UserGusto gustoCliente = gustosCliente.get(j);
					if (gusto.getId() == gustoCliente.getId()) {
						gusto.setGustado(true);
						break;
					}
				}
			}
			mav.addObject("gustos", gustos);
			break;
		case 24:
			// TIPO TRANSFERIR
			html += viewApp.loadViews("transfiere-scotiapesos", "FOOTER");
			mav = new ModelAndView(html);
			break;
		default:
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);
		categoryHeaderSetter.setHeaders(mav);
		return mav;
	}

	@GetMapping("/information/{nombreInformation}")
	public ModelAndView getinformation(@PathVariable("nombreInformation") String nombreInformation) throws ViewRendererException {
		ModelAndView mav = new ModelAndView(viewApp.loadViews("HEAD", "INFORMATION", "FOOTER"));
		Information informationhtml = new Information();
		informationhtml = dtserver.loadInformationByName(nombreInformation);
		if (informationhtml == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		mav.addObject("informationhtml", informationhtml);
		categoryHeaderSetter.setHeaders(mav);
		return mav;
	}

	@GetMapping("/cupon/get/{id_reward}")
	public ModelAndView getCuponByRew(@PathVariable("id_reward") int id_reward,
			@RequestHeader(value = "referer", required = false) final String referer) throws ViewRendererException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (resolver.isAnonymous(auth)) {
			return new ModelAndView("redirect:" + referer + "?login");
		}

		ModelAndView mav = new ModelAndView(viewApp.loadViews("HEAD", "INFORMATION", "FOOTER"));
		categoryHeaderSetter.setHeaders(mav);
		return mav;
	}

	@GetMapping("/getcupon/{id_rew}")
	public Object getFile(@PathVariable("id_rew") int idReward, HttpServletRequest rq,
			@RequestHeader(value = "referer", required = false) final String referer) {
		Customer customer = new Customer();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (!resolver.isAnonymous(auth)) {
			customer = (Customer) auth.getPrincipal();
		} else {
			// Para evitar que una página quede como ?login?login se hace el replace
			return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
		}
		byte[] response = customerModel.loadCuponAsPdf(customer.getScotiauser().getIdCliente(), idReward);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(new ByteArrayResource(response));
	}

	@PostMapping("/gustos/actualizar")
	public void actualizarGustos(@RequestParam(value = "gusto", required = false) String[] gustos) {
		if (gustos == null) {
			gustos = new String[0];
		}
		customerModel.saveCustomerGustos(gustos);
	}

}
