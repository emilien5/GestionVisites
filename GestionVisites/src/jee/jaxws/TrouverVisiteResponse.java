
package jee.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.2.2
 * Fri Mar 23 14:21:48 CET 2018
 * Generated source version: 3.2.2
 */

@XmlRootElement(name = "trouverVisiteResponse", namespace = "http://jee/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trouverVisiteResponse", namespace = "http://jee/")

public class TrouverVisiteResponse {

    @XmlElement(name = "return")
    private java.util.ArrayList<jee.Visite> _return;

    public java.util.ArrayList<jee.Visite> getReturn() {
        return this._return;
    }

    public void setReturn(java.util.ArrayList<jee.Visite> new_return)  {
        this._return = new_return;
    }

}
