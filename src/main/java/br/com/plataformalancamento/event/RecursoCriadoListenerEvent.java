package br.com.plataformalancamento.event;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class RecursoCriadoListenerEvent implements ApplicationListener<RecursoCriadoEvent>{

	@Override
	public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
		HttpServletResponse httpServletResponse = recursoCriadoEvent.getHttpServletResponse();
		Long codigo = recursoCriadoEvent.getCodigo();
		adicionarHeaderLocation(httpServletResponse, codigo);
	}

	private void adicionarHeaderLocation(HttpServletResponse httpServletResponse, Long codigo) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(codigo).toUri();
		httpServletResponse.setHeader("Location", uri.toASCIIString());
	}

}
