<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="TeamDetailsg_sv">
<t:beanprocessing id="g_1" beanbinding="#{d.TeamDetailsUI}" />
<t:rowheader id="g_2" coldistance="5" padding="left:20;top:7;bottom:7" >
<t:button id="g_3" actionListener="#{d.TeamDetailsUI.onSaveAction}" text="Save" width="100+" />
<t:coldistance id="g_4" />
<t:button id="g_5" actionListener="#{d.TeamDetailsUI.onDeleteAction}" enabled="#{d.TeamDetailsUI.btnDelEnabled}" text="Delete" width="100" />
<t:coldistance id="g_6" />
<t:button id="g_7" actionListener="#{d.TeamDetailsUI.onCancelAction}" text="Cancel" width="100+" />
</t:rowheader>
<t:row id="g_8" >
<t:pane id="g_9" height="100%" padding="20" rowdistance="13" width="100%" >
<t:row id="g_10" >
<t:label id="g_11" text="Name" width="100" />
<t:field id="g_12" bgpaint="#{d.TeamDetailsUI.bgPaints.nameBgPaint}" text="#{d.TeamDetailsUI.team.name}" width="100%" />
</t:row>
<t:row id="g_13" >
<t:label id="g_14" text="Established" width="100" />
<t:calendarfield id="g_15" bgpaint="#{d.TeamDetailsUI.bgPaints.establishedBgPaint}" timezone="LOCAL" value="#{d.TeamDetailsUI.team.established}" width="100" />
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
