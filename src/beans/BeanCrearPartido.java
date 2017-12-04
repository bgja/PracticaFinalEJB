package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.Date;
import java.util.Collection;
import java.util.List;

import modelo.Temporada;
import controlador.ControladorLocal;

@ManagedBean(name = "beanCrearPartido")
@SessionScoped
public class BeanCrearPartido {

	private String temporadaId;

	private Date fecha;

	private Collection<Temporada> temporadas;

	

	public String getTemporadaId() {
		return temporadaId;
	}

	public void setTemporadaId(String temporadaId) {
		this.temporadaId = temporadaId;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setTemporadas(Collection<Temporada> temporadas) {
		this.temporadas = temporadas;
	}

	public Collection<Temporada> getTemporadas() {
		List<Temporada> todasTemporadas = ControladorLocal.getUnicaInstancia()
				.listarTemporadas();
		for (Temporada temporada : todasTemporadas) {
			this.temporadas.add(temporada);
		}
		return this.temporadas;
	}

	public String submit() {
		ControladorLocal.getUnicaInstancia().registrarPartido(fecha, temporadaId);
		return "partidoLista";
	}

}
