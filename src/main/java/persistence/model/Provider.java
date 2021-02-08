package main.java.persistence.model;

import java.util.Date;
import java.util.Objects;

public class Provider {

    private Integer id;
    private String name;
    private Date dischargeDate;
    private Integer clientId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dischargeDate=" + dischargeDate +
                ", clientId=" + clientId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider proveedor = (Provider) o;
        return Objects.equals(id, proveedor.id) && Objects.equals(name, proveedor.name) && Objects.equals(dischargeDate, proveedor.dischargeDate) && Objects.equals(clientId, proveedor.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dischargeDate, clientId);
    }
}
