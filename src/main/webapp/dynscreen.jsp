<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="dynscreeng_sv">
<t:beanprocessing id="g_1" beanbinding="#{d.DynScreenUI}" />
<t:rowtitlebar id="g_2" />
<t:rowheader id="g_3" />
<t:rowbodypane id="g_4" >
<t:row id="g_5" >
<t:button id="g_6" actionListener="#{d.DynScreenUI.onCase1Action}" text="Case 1" />
<t:coldistance id="g_7" />
<t:button id="g_8" actionListener="#{d.DynScreenUI.onCase2Action}" text="Case 2" />
</t:row>
<t:rowdistance id="g_9" height="30" />
<t:rowdynamiccontent id="g_10" contentbinding="#{d.DynScreenUI.dynContent}" />
</t:rowbodypane>
<t:rowstatusbar id="g_11" />
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
