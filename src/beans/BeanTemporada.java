package beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import controlador.ControladorLocal;
import modelo.Temporada;
import modelo.Usuario;

@ManagedBean(name="beanTemporada")
@SessionScoped
public class BeanTemporada {

	private String nombre;
	private String lugar;
	private int minimo;
	private Collection<Usuario> usuarios;
	private String[] checked;

	public BeanTemporada() {
		this.usuarios = new LinkedList<Usuario>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public Collection<Usuario> getUsuarios() {
		this.usuarios = new LinkedList<Usuario>();
		this.usuarios.addAll(ControladorLocal.getUnicaInstancia().listarUsuarios());
		return this.usuarios;
		/*List<Usuario> todosUsuarios = Controlador.getUnicaInstancia().listarUsuarios();
		for (Usuario usuario : todosUsuarios) {
			usuarios.add(usuario);
		}
		return usuarios;*/
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String submit() {
		if (ControladorLocal.getUnicaInstancia().obtenerTemporada(nombre) != null){
			FacesContext.getCurrentInstance().addMessage("nombre", new FacesMessage("Nombre ya utilizado."));
			return "temporadaFallo";
		}
		else {
			ControladorLocal.getUnicaInstancia().registroTemporada(nombre, lugar, minimo);
			for (Usuario usuario : usuarios) {
				for(int i = 0; i<this.checked.length; i++){
					if (checked[i] == usuario.getUsuario())
						ControladorLocal.getUnicaInstancia().anadirUsuarioTemporada(nombre, usuario.getUsuario());
				}
				
			}
			return "temporadaLista";
		}
	}

	public String[] getChecked() {
		return checked;
	}

	public void setChecked(String[] checked) {
		this.checked = checked;
	}
	
	
}
