<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="dynscreen2g_sv">
<t:beanprocessing id="g_2" beanbinding="#{d.Dynscreen2UI}" />
<t:rowtitlebar id="g_3" />
<t:rowheader id="g_4" />
<t:rowbodypane id="g_5" >
<t:row id="g_6" >
<t:button id="g_7" actionListener="#{d.Dynscreen2UI.onAddRowAction}" text="Add Row" />
</t:row>
<t:rowdistance id="g_8" height="30" />
<t:rowdynamiccontent id="g_9" contentbinding="#{d.Dynscreen2UI.dynContent}" />
</t:rowbodypane>
<t:rowstatusbar id="g_10" />
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
