<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="testpageg_sv">
<t:beanprocessing id="g_1" beanbinding="#{d.TestpageUI}" />
<t:rowtitlebar id="g_2" />
<t:rowheader id="g_3" />
<t:rowbodypane id="g_4" rowdistance="20" >
<t:row id="g_5" >
<t:button id="g_6" actionListener="#{d.TestpageUI.onButtonAction}" text="SeedTeams" />
</t:row>
<t:row id="g_7" >
<t:button id="g_8" actionListener="#{d.TestpageUI.onTest2Action}" text="Test2" />
</t:row>
</t:rowbodypane>
<t:rowstatusbar id="g_9" />
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
