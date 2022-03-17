package ActividadRelaciones1_N.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Contratos")
public class Contrato implements Serializable {

    @Id
    @Column(name = "coNumContrato", nullable = false)
    private int numContrato;

    @Column(name = "coFecContrato", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecContrato;

    @Column(name = "coDuracion", nullable = false)
    private int duracion;

    @Column(name = "coFichaAnual", nullable = false)
    private int fichaAnual;

    @Column(name = "coClausula", nullable = false)
    private int clausula;

    @ManyToOne
    @JoinColumn(name = "coNbEquipo")
    private Equipo eq;

    @ManyToOne
    @JoinColumn(name = "coNumFicha")
    private Futbolista fut;

    public Contrato() {
        super();
    }

    public Contrato(int numContrato, Date fecContrato, int duracion, int coFichaAnual, int coClausula, Equipo eq, Futbolista fut) {
        this.numContrato = numContrato;
        this.fecContrato = fecContrato;
        this.duracion = duracion;
        this.fichaAnual = coFichaAnual;
        this.clausula = coClausula;
        this.eq = eq;
        this.fut = fut;
    }

    public Contrato(int numContrato, Date fecContrato, int duracion, int coFichaAnual, int coClausula) {
        this.numContrato = numContrato;
        this.fecContrato = fecContrato;
        this.duracion = duracion;
        this.fichaAnual = coFichaAnual;
        this.clausula = coClausula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrato contrato = (Contrato) o;
        return numContrato == contrato.numContrato;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numContrato);
    }

    public int getNumContrato() {
        return numContrato;
    }

    public void setNumContrato(int numContrato) {
        this.numContrato = numContrato;
    }

    public Date getFecContrato() {
        return fecContrato;
    }

    public void setFecContrato(Date fecContrato) {
        this.fecContrato = fecContrato;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getFichaAnual() {
        return fichaAnual;
    }

    public void setFichaAnual(int coFichaAnual) {
        this.fichaAnual = coFichaAnual;
    }

    public int getClausula() {
        return clausula;
    }

    public void setClausula(int coClausula) {
        this.clausula = coClausula;
    }

    public Equipo getEq() {
        return eq;
    }

    public void setEq(Equipo eq) {
        this.eq = eq;
    }

    public Futbolista getFut() {
        return fut;
    }

    public void setFut(Futbolista fut) {
        this.fut = fut;
    }

    @Override
    public String toString() {
        return "Contrato{" +
                "numContrato=" + numContrato +
                ", FecContrato=" + fecContrato +
                ", duracion=" + duracion +
                ", coFichaAnual=" + fichaAnual +
                ", coClausula=" + clausula +
                '}';
    }
}
