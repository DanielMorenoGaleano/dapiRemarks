package com.avianca.pagos.rest.handler;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

@Component
public class ResponseHandler {
	@Handler
	public void process(Exchange exchange) throws Exception {

//		Map<String, Object> map = (Map<String, Object>) exchange.getIn().getHeader("CamelCxfMessage");
		String code = exchange.getIn().getHeader("code").toString();
		String response = exchange.getIn().getBody().toString();

		if (code.equals("200")) {
			exchange.getOut().setHeader("BOperacionExitosa", true);
			exchange.getOut().setHeader("SCodigo", code);
			exchange.getOut().setHeader("SMensaje", "Operacion Exitosa");
			exchange.getOut().setHeader("SMensajeTecnico", "Operacion Exitosa al procesar la solicitud");
		} else if (code.equals("203")) {
			exchange.getOut().setHeader("BOperacionExitosa", false);
			exchange.getOut().setHeader("SCodigo", "204");
			exchange.getOut().setHeader("SMensaje", "Operación Exitosa sin resultados");
			exchange.getOut().setHeader("SMensajeTecnico", "Operacion ya fue procesada");
		} else if (code.equals("204")) {
			exchange.getOut().setHeader("BOperacionExitosa", false);
			exchange.getOut().setHeader("SCodigo", code);
			exchange.getOut().setHeader("SMensaje", "Operación Exitosa sin resultados");
			exchange.getOut().setHeader("SMensajeTecnico", "No se encuentran resultados");
		} else if (code.equals("408")) {
			exchange.getOut().setHeader("BOperacionExitosa", false);
			exchange.getOut().setHeader("SCodigo", code);
			exchange.getOut().setHeader("SMensaje", "Error de timeout");
			exchange.getOut().setHeader("SMensajeTecnico", "Conexion no exitosa");
		} else if (code.equals("500")) {
			exchange.getOut().setHeader("BOperacionExitosa", false);
			exchange.getOut().setHeader("SCodigo", code);
			exchange.getOut().setHeader("SMensaje", "Error no controlado");
			exchange.getOut().setHeader("SMensajeTecnico", "Error no controlado se envia notificacion");
		} else if (code.equals("400")) {
			exchange.getOut().setHeader("BOperacionExitosa", false);
			exchange.getOut().setHeader("SCodigo", code);
			exchange.getOut().setHeader("SMensaje", "Request erroneo o vacio");
			exchange.getOut().setHeader("SMensajeTecnico", "Request erroneo o vacio");
		} else if (code.equals("401")) {
			exchange.getOut().setHeader("BOperacionExitosa", false);
			exchange.getOut().setHeader("SCodigo", "400");
			exchange.getOut().setHeader("SMensaje", "Campo OrderId ó  lastName invalido");
			exchange.getOut().setHeader("SMensajeTecnico", "Campo OrderId ó  lastName invalido");
		}
		exchange.getOut().setBody(response, String.class);
		exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, code);
		exchange.getOut().setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json; charset=UTF-8");
		exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json; charset=UTF-8");

	}
}
