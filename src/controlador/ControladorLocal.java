package controlador;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.eclipse.persistence.internal.databaseaccess.DatasourcePlatform;

import modelo.Alineacion;
import modelo.Color;
import modelo.Partido;
import modelo.Temporada;
import modelo.Usuario;

public class ControladorLocal {
	private static ControladorLocal unicaInstancia;
	private ControladorRemoto controlador;

	private ControladorLocal(){
		try {
			controlador = (ControladorRemoto) new InitialContext().lookup("java:global/PracticaEJB/ControladorRemoto");
			} catch (NamingException e) {
			e.printStackTrace();
			}
	}
	
	public static ControladorLocal getUnicaInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new ControladorLocal();
		}
		return unicaInstancia;
	}

	public Usuario registrarUsuario(String usuario, String clave, String mail, String movil){
		return controlador.registrarUsuario(usuario, clave, mail, movil);
	}
	
	public boolean login(String usuario, String clave){
		return controlador.login(usuario, clave);
	}
	
	public Usuario obtenerUsuario(String usuario){
		return controlador.obtenerUsuario(usuario);
	}
	
	public Usuario actualizarUsuario(String usuario, String clave, String mail, String movil){
		return controlador.actualizarUsuario(usuario, clave, mail, movil);
	}
	
	public List<Usuario> listarUsuarios(){
		return controlador.listarUsuarios();
	}
	
	public Temporada registroTemporada(String nombre, String lugar, int minimo){
		return controlador.registroTemporada(nombre, lugar, minimo);
	}
	
	public List<Temporada> listarTemporadas(){
		return controlador.listarTemporadas();
	}
	
	public Temporada anadirUsuarioTemporada(String temporada, String usuario){
		return controlador.anadirUsuarioTemporada(temporada, usuario);
	}
	
	public Temporada quitarUsuarioTemporada(String temporada, String usuario){
		return controlador.quitarUsuarioTemporada(temporada, usuario);
	}
	
	public Temporada obtenerTemporada(String temporada){
		return controlador.obtenerTemporada(temporada);
	}
	
	public Partido obtenerPartido(Long partido){
		return controlador.obtenerPartido(partido);
	}
	
	public Partido registrarPartido(Date fecha, String temporada){
		return controlador.registrarPartido(fecha, temporada);
	}
	
	public Partido confirmarAsistencia(Long id, String usuario){
		return controlador.confirmarAsistencia(id, usuario);
	}
	
	public Alineacion registrarAlineacion(Long idPartido, String nombre, Color color, int tanteo){
		return controlador.registrarAlineacion(idPartido, nombre, color, tanteo);
	}
	
	public Alineacion registrarUsuariosAlineacion(String usuario, String alineacion){
		return controlador.registrarUsuariosAlineacion(usuario, alineacion);
		
	}
	
	public List<Partido> obtenerPartidoEntreFechas(Date f1, Date f2){
		return controlador.obtenerPartidoEntreFechas(f1, f2);
		
	}
	
	public List<Partido> obtenerPartidoTemporada(String temporada){
		return controlador.obtenerPartidoTemporada(temporada);
	}
}
