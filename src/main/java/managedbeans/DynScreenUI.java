package managedbeans;

import java.io.Serializable;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.componentnodes.PANENode;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding;
import org.eclnt.jsfserver.pagebean.PageBean;
import org.eclnt.jsfserver.elements.componentnodes.*;

import javax.faces.event.ActionEvent;

@CCGenClass (expressionBase="#{d.DynScreenUI}")

public class DynScreenUI extends PageBean implements Serializable {
    // ------------------------------------------------------------------------
    // inner classes
    // ------------------------------------------------------------------------
    
    /* Listener to the user of the page bean. */
    public interface IListener
    {
    }
    
    // ------------------------------------------------------------------------
    // members
    // ------------------------------------------------------------------------
    
    private IListener m_listener;
    ROWDYNAMICCONTENTBinding m_dynContent = new ROWDYNAMICCONTENTBinding();
    
    // ------------------------------------------------------------------------
    // constructors & initialization
    // ------------------------------------------------------------------------

    public DynScreenUI() {
    }

    public String getPageName() { return "/dynscreen.jsp"; }
    public String getRootExpressionUsedInPage() { return "#{d.DynScreenUI}"; }

    // ------------------------------------------------------------------------
    // public usage
    // ------------------------------------------------------------------------

    /* Initialization of the bean. Add any parameter that is required within your scenario. */
    public void prepare(IListener listener)
    {
        m_listener = listener;
    }
    public ROWDYNAMICCONTENTBinding getDynContent() { return m_dynContent; }

    public void onCase1Action(javax.faces.event.ActionEvent event) {
        String s = "<t:row>";
        s = s.concat("<t:button text=\"Button 1\" /><t:button text=\"Button 2\" />");
        s = s.concat("</t:row>");
        m_dynContent.setContentXml(s);
    }

    public void onCase2Action(javax.faces.event.ActionEvent event) {
        // String s = "<t:row>";
        // s = s.concat("<t:button text=\"Button 1\" /><t:button text=\"Button 2\" /><t:button text=\"Button 3\" />");
        // s = s.concat("</t:row>");
        // m_dynContent.setContentXml(s);
        String[] fields = new String[]{"firstName", "lastName"};
        PANENode paneNode = new PANENode().setWidth("100%").setBorder("color:#808080");
        for (String field : fields) {
            ROWNode r = new ROWNode();
            paneNode.addSubNode(r);
            LABELNode l = new LABELNode().setText(field).setWidth("100");
            r.addSubNode(l);
        }
        m_dynContent.setContentNode(paneNode);
    }

    // ------------------------------------------------------------------------
    // private usage
    // ------------------------------------------------------------------------
}
