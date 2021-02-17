package uni.bamberg.appengine.model;

import java.io.Serializable;

public final class Timing implements Serializable {

    private  String fromdate;

    private  String todate;

 
    
    public Timing() {
		super();
	}

    public Timing(String fromdate, String todate) {
	this.fromdate = fromdate;
	this.todate = todate;
    }

    public String getFromdate() {
	return this.fromdate;
    }

    public String getTodate() {
	return this.todate;
    }


}
