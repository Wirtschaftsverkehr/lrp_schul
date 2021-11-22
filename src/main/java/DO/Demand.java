package DO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Demand implements Serializable {

    private Integer demand_dry;
    //private Integer demand_fresh;

    public Demand(Integer demand_dry) {
        this.demand_dry = demand_dry;
        //this.demand_fresh = demand_fresh;
    }
}
