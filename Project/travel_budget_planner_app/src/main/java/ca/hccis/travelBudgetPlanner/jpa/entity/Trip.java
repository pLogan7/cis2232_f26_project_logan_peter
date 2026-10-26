package ca.hccis.travelBudgetPlanner.jpa.entity;

import ca.hccis.travelBudgetPlanner.bo.TripBO;

import ca.hccis.travelBudgetPlanner.util.CisUtility;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import jakarta.ws.rs.DefaultValue;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "tripDetails", schema = "cis22332_travel_budget_planner")
public class Trip{
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}