package util;

import org.eclnt.jsfserver.elements.BaseComponentTag;
import org.eclnt.jsfserver.elements.macros.IMacro;

public class CISMacro implements IMacro {

    @Override
    public void executeMacro(BaseComponentTag tag, String[] params) {
        // #{d.TeamDetailsUI.team.name}
        // #{d.TeamDetailsUI.team.established}
        String s = "";
        if (tag.getTagName().equals("field")) {
            s = tag.getAttributeFromAttributeMap("text");
        }
        if (tag.getTagName().equals("calendarfield")) {
            s = tag.getAttributeFromAttributeMap("value");
        }
        if (s.equals("")) return;
        // find name
        int lastIndexOfDot = s.lastIndexOf(".");
        // name
        String propertyName = s.substring(lastIndexOfDot + 1, s.length() - 1);
        // #{d.TeamDetailsUI
        int indexDotTeam = s.indexOf(".team.");
        String baseExpression = s.substring(0, indexDotTeam);
        // bgpaint -> #{d.TeamDetailsUI.cis.name.bgPaint}
        tag.setAttributeInAttributeMap("bgpaint", baseExpression + ".cis." + propertyName + ".bgPaint}");
        // border -> #{d.TeamDetailsUI.cis.name.border}
        tag.setAttributeInAttributeMap("border", baseExpression + ".cis." + propertyName + ".border}");
        // tooltip -> #{d.TeamDetailsUI.cis.name.tooltip}
        tag.setAttributeInAttributeMap("tooltip", baseExpression + ".cis." + propertyName + ".tooltip}");
    }

    @Override
    public boolean checkIfApplicable(String controlName) {
        if ("t:field".equals(controlName)) return true;
        if ("t:calendarfield".equals(controlName)) return true;
        return false;
    }

    @Override
    public boolean checkIfAttributeIsAffected(String attributeName) {
        if ("bgpaint".equals(attributeName)) return true;
        if ("border".equals(attributeName)) return true;
        if ("tooltip".equals(attributeName)) return true;
        return false;
    }

    @Override
    public String getName() {
        return "CISMacro";
    }

    @Override
    public String[] getMacroParamNames() {
        return new String[0];
    }
}
