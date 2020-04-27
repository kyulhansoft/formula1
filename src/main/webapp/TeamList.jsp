<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="TeamListg_sv">
<t:beanprocessing id="g_1" beanbinding="#{d.TeamListUI}" />
<t:rowtitlebar id="g_2" text="Team List" />
<t:rowheader id="g_3" />
<t:rowbodypane id="g_4" padding="0" >
<t:row id="g_5" >
<t:splitpane id="g_6" height="100%" orientation="horizontal" width="100%" >
<t:splitpanesplit id="g_7" >
<t:row id="g_8" >
<t:button id="g_9" actionListener="#{d.TeamListUI.onNewTeamAction}" text="New Team" width="100" />
</t:row>
<t:row id="g_10" >
<t:fixgrid id="g_11" height="100%" objectbinding="#{d.TeamListUI.grid}" width="100%" >
<t:gridcol id="g_12" text="Name" width="100%;100" >
<t:label id="g_13" text=".{team.name}" />
</t:gridcol>
<t:gridcol id="g_14" text="Fond Date" width="100%;50" >
<t:label id="g_15" format="date" text=".{team.established}" />
</t:gridcol>
</t:fixgrid>
</t:row>
</t:splitpanesplit>
<t:splitpanesplit id="g_16" >
<t:rowpagebeaninclude id="g_17" pagebeanbinding="#{d.TeamListUI.teamDetailsUI}" shownullcontent="false" />
</t:splitpanesplit>
</t:splitpane>
</t:row>
</t:rowbodypane>
<t:rowstatusbar id="g_18" />
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
