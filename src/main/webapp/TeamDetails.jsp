<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="TeamDetailsg_sv">
<t:beanprocessing id="g_2" beanbinding="#{d.TeamDetailsUI}" />
<t:rowheader id="g_3" coldistance="5" padding="left:20;top:7;bottom:7" >
<t:button id="g_4" actionListener="#{d.TeamDetailsUI.onSaveAction}" text="Save" width="100+" />
<t:coldistance id="g_1" />
<t:button id="g_16" actionListener="#{d.TeamDetailsUI.onDeleteAction}" enabled="#{d.TeamDetailsUI.btnDelEnabled}" text="Delete" width="100" />
<t:coldistance id="g_5" />
<t:button id="g_6" actionListener="#{d.TeamDetailsUI.onCancelAction}" text="Cancel" width="100+" />
</t:rowheader>
<t:row id="g_7" >
<t:pane id="g_8" height="100%" padding="20" rowdistance="13" width="100%" >
<t:row id="g_9" >
<t:label id="g_10" text="Name" width="100" />
<t:field id="g_11" text="#{d.TeamDetailsUI.team.name}" width="100%" />
</t:row>
<t:row id="g_12" >
<t:label id="g_13" text="Established" width="100" />
<t:calendarfield id="g_14" timezone="LOCAL" value="#{d.TeamDetailsUI.team.established}" width="100" />
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
