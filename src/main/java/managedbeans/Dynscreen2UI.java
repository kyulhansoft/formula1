package managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.pagebean.PageBean;

import javax.faces.event.ActionEvent;
import org.eclnt.jsfserver.elements.componentnodes.BUTTONNode;
import org.eclnt.jsfserver.elements.componentnodes.COLDISTANCENode;
import org.eclnt.jsfserver.elements.componentnodes.FIELDNode;
import org.eclnt.jsfserver.elements.componentnodes.LABELNode;
import org.eclnt.jsfserver.elements.componentnodes.PANENode;
import org.eclnt.jsfserver.elements.componentnodes.ROWNode;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding;
import org.eclnt.jsfserver.util.valuemgmt.IActionListenerDelegation;

@CCGenClass (expressionBase="#{d.Dynscreen2UI}")

public class Dynscreen2UI extends PageBean implements Serializable {
    // ------------------------------------------------------------------------
    // inner classes
    // ------------------------------------------------------------------------
    
    /* Listener to the user of the page bean. */
    public interface IListener {
    }
    
    // ------------------------------------------------------------------------
    // members
    // ------------------------------------------------------------------------
    
    class DynStruct {
        int i_id;
        String i_label;
        String i_input;
        String i_button;
    }
    
    private IListener m_listener;
    ROWDYNAMICCONTENTBinding m_dynContent = new ROWDYNAMICCONTENTBinding();
    PANENode m_paneNode;
    List<DynStruct> m_dynList = new ArrayList<>();
    private int m_id;
    
    // ------------------------------------------------------------------------
    // constructors & initialization
    // ------------------------------------------------------------------------

    public Dynscreen2UI() {
        System.out.println("Dynscreen2UI()");
        m_paneNode = new PANENode().setWidth("100%").setHeight("100%")
            .setBackground("#ffffff").setBorder("color:#808080").setRowdistance(13)
            .setPadding("20");
        m_dynContent.setContentNode(m_paneNode);
        
        initDynItems();
        render();
    }

    public String getPageName() { return "/dynscreen2.jsp"; }
    public String getRootExpressionUsedInPage() { return "#{d.Dynscreen2UI}"; }

    // ------------------------------------------------------------------------
    // public usage
    // ------------------------------------------------------------------------
    
    public int genId() {
        return (++m_id);
    }

    /* Initialization of the bean. Add any parameter that is required within your scenario. */
    public void prepare(IListener listener) {
        m_listener = listener;
    }
    
    public void initDynItems() {
        for (int i = 0; i < 5; i++) {
            DynStruct el = new DynStruct();
            el.i_id = genId();
            el.i_label = "Label " + i;
            el.i_input = "Input " + i;
            el.i_button = "Button " + i;
            m_dynList.add(el);
        }
    }
    
    public void removeItem(int id) {
        for (DynStruct el : m_dynList) {
            if (el.i_id == id) {
                m_dynList.remove(el);
                break;
            }
        }
        render();
    }
    
    public void addItem() {
        int i = genId();
        DynStruct el = new DynStruct();
        el.i_id = i;
        el.i_label = "Label " + i;
        el.i_input = "Input " + i;
        el.i_button = "Button " + i;
        m_dynList.add(el);
        render();
    }
    
    public ROWDYNAMICCONTENTBinding getDynContent() { return m_dynContent; }
    
    public void onAddRowAction(javax.faces.event.ActionEvent event) {
        System.out.println("onAddRowAction");
        addItem();
    }
    
    public void onRemoveRowAction(javax.faces.event.ActionEvent event) {
        System.out.println("onRemoveRowAction");
        UIComponent component =  event.getComponent();
        //component.get
    }
    
    private void render() {
        m_paneNode = new PANENode().setWidth("100%").setHeight("100%")
            .setBackground("#ffffff").setBorder("color:#808080").setRowdistance(13)
            .setPadding("20");
        for (DynStruct el : m_dynList) {
            ROWNode row = new ROWNode().setColdistance(20);
         
            LABELNode label = new LABELNode().setText(el.i_label).setWidth("100");
            row.addSubNode(label);
            
            FIELDNode field = new FIELDNode().setWidth("150");
            row.addSubNode(field);
            
            BUTTONNode button = new BUTTONNode().setText("Remove")
                .bindActionListener(new IActionListenerDelegation() {
                    @Override
                    public void onAction(ActionEvent arg0) {
                        m_dynList.remove(el);
                        render();
                    }
                });
                //.setActionListener("#{d.Dynscreen2UI.onRemoveRowAction}");
            row.addSubNode(button);
        
            m_paneNode.addSubNode(row);
        }
        m_dynContent.setContentNode(m_paneNode);
    }

    // ------------------------------------------------------------------------
    // private usage
    // ------------------------------------------------------------------------
}
