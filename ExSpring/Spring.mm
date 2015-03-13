<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node COLOR="#1c801c" CREATED="1400561190584" ID="ID_1525807073" MODIFIED="1406650926793" STYLE="bubble" TEXT="Spring">
<edge COLOR="#1c801c" STYLE="sharp_bezier" WIDTH="8"/>
<font BOLD="true" NAME="SansSerif" SIZE="24"/>
<icon BUILTIN="licq"/>
<node CREATED="1405478804142" ID="ID_387120520" MODIFIED="1411013744422" POSITION="right" TEXT="Servlet">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node CREATED="1405478810102" ID="ID_1467898641" MODIFIED="1405478812658" TEXT="Context"/>
<node CREATED="1405478813238" ID="ID_1516398489" MODIFIED="1405478815289" TEXT="Session"/>
<node CREATED="1405478815630" ID="ID_601370829" MODIFIED="1405478818448" TEXT="Cookie"/>
<node CREATED="1405483166199" ID="ID_19416217" MODIFIED="1405483169107" TEXT="Upload"/>
<node CREATED="1405483169687" ID="ID_689658089" MODIFIED="1405483173043" TEXT="Mail"/>
<node CREATED="1406103840742" FOLDED="true" ID="ID_1158521720" MODIFIED="1411013783909" TEXT="Listener">
<node CREATED="1406103860965" ID="ID_1773675651" MODIFIED="1410149451066" TEXT="Session">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      HttpSessionHandledListener
    </p>
  </body>
</html></richcontent>
<node CREATED="1406103888253" ID="ID_1377569983" MODIFIED="1406103889049" TEXT="HttpSessionListener">
<node CREATED="1406103925101" ID="ID_489655884" MODIFIED="1406105806560" TEXT="sessionCreated(HttpSessionEvent)">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Raise on client connect at the first time.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1406103933917" ID="ID_1005185266" MODIFIED="1406105802258" TEXT="sessionDestroyed(HttpSessionEvent)">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Raise on session time out.
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1406103895861" ID="ID_1374791490" MODIFIED="1406103896353" TEXT="HttpSessionAttributeListener">
<node CREATED="1406103941269" ID="ID_1412428440" MODIFIED="1406104002720" TEXT="attributeAdded(HttpSessionBindingEvent)"/>
<node CREATED="1406103947237" ID="ID_1687365595" MODIFIED="1406106017306" TEXT="attributeRemoved(HttpSessionBindingEvent)">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      !Can not access to the attribute that is removed.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1406103955325" ID="ID_1045310696" MODIFIED="1406104011344" TEXT="attributeReplaced(HttpSessionBindingEvent)"/>
</node>
<node CREATED="1406103903517" ID="ID_1308373795" MODIFIED="1406103904729" TEXT="ApplicationContextAware">
<node CREATED="1406104025588" ID="ID_1707888330" MODIFIED="1406104048320" TEXT="setApplicationContext(ApplicationContext)">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Add context in Sevlet3.0 instead of xml-config in Servlet2.5
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1406105342747" ID="ID_1600876116" MODIFIED="1410149453111" TEXT="Context">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      ServletContextHandleListener
    </p>
  </body>
</html></richcontent>
<node CREATED="1406105352899" ID="ID_366434491" MODIFIED="1406105353879" TEXT="ServletContextListener">
<node CREATED="1406105375323" ID="ID_145991560" MODIFIED="1406105729338" TEXT="contextInitialized(ServletContextEvent)">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Raise on server start
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1406105386210" ID="ID_97091731" MODIFIED="1406105744162" TEXT="contextDestroyed(ServletContextEvent)">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Raise on server stop
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1406105359987" ID="ID_1767428657" MODIFIED="1406105361079" TEXT="ServletContextAttributeListener">
<node CREATED="1406105401482" ID="ID_11159932" MODIFIED="1406105403430" TEXT="attributeAdded(ServletContextAttributeEvent)"/>
<node CREATED="1406105411010" ID="ID_820250620" MODIFIED="1406106022798" TEXT="attributeRemoved(ServletContextAttributeEvent)">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      !Can not access to the attribute that is removed.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1406105420770" ID="ID_1504086949" MODIFIED="1406105422701" TEXT="attributeReplaced(ServletContextAttributeEvent)"/>
</node>
</node>
<node CREATED="1406107201022" ID="ID_919490144" MODIFIED="1410149454247" TEXT="Request">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      HttpServletRequestListener
    </p>
  </body>
</html></richcontent>
<node CREATED="1406107225222" ID="ID_229676109" MODIFIED="1406107226586" TEXT="ServletRequestListener">
<node CREATED="1406107244622" ID="ID_1430661661" MODIFIED="1406107273785" TEXT="requestInitialized(ServletRequestEvent)"/>
<node CREATED="1406107262013" ID="ID_897600468" MODIFIED="1406107271001" TEXT="requestDestroyed(ServletRequestEvent)"/>
</node>
<node CREATED="1406107233374" ID="ID_1127758231" MODIFIED="1406107234130" TEXT="ServletRequestAttributeListener">
<node CREATED="1406107295941" ID="ID_1567488814" MODIFIED="1406107297698" TEXT="attributeAdded(ServletRequestAttributeEvent)"/>
<node CREATED="1406107283197" ID="ID_483316429" MODIFIED="1406107333049" TEXT="attributeRemoved(ServletRequestAttributeEvent"/>
<node CREATED="1406107342261" ID="ID_1424611847" MODIFIED="1406107343945" TEXT="attributeReplaced(ServletRequestAttributeEvent)"/>
</node>
</node>
</node>
</node>
<node COLOR="#1c801c" CREATED="1405478323966" ID="ID_1472797539" MODIFIED="1411013786843" POSITION="right" STYLE="bubble" TEXT="Core">
<edge COLOR="#1c801c" STYLE="sharp_bezier" WIDTH="8"/>
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<icon BUILTIN="wizard"/>
<node CREATED="1406086020914" ID="ID_1078679770" MODIFIED="1410148896831" TEXT="Bean">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      http://stackoverflow.com/questions/6827752/whats-the-difference-between-component-repository-service-annotations-in
    </p>
  </body>
</html></richcontent>
<node CREATED="1410148707973" ID="ID_1483533929" MODIFIED="1410150573032" TEXT="@Scope">
<icon BUILTIN="help"/>
<node CREATED="1411014626199" ID="ID_1457495666" MODIFIED="1411014661552" TEXT="singleton">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Return a single bean instance per Spring IoC container
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1411014672077" ID="ID_1783814646" MODIFIED="1411014692670" TEXT="prototype">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Return a new bean instance each time when requested
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1411014693973" ID="ID_770434484" MODIFIED="1411014704433" TEXT="request">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Return a single bean instance per HTTP request. *
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1411014721989" ID="ID_698243544" MODIFIED="1411014733411" TEXT="session">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Return a single bean instance per HTTP session. *
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1411014744588" ID="ID_820819156" MODIFIED="1411014755107" TEXT="globalSession">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Return a single bean instance per global HTTP session. *
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1406086229152" ID="ID_85737639" MODIFIED="1410148168705" TEXT="@Component">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      generic stereotype for any Spring-managed component
    </p>
  </body>
</html></richcontent>
<node CREATED="1410148215352" ID="ID_1310601106" MODIFIED="1410148220732" TEXT="Any layer"/>
<node CREATED="1410148853243" ID="ID_279288437" MODIFIED="1410148854127" TEXT="Indicates a auto scan component."/>
</node>
<node CREATED="1410147974596" ID="ID_1683309387" MODIFIED="1410148204845" TEXT="@Service">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      stereotype for service layer
    </p>
  </body>
</html></richcontent>
<node CREATED="1410148222128" ID="ID_757129423" MODIFIED="1410148234876" TEXT="Service layer"/>
</node>
<node CREATED="1410147980002" ID="ID_1066086086" MODIFIED="1410148190901" TEXT="@Repository">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      stereotype for persistence layer
    </p>
  </body>
</html></richcontent>
<node CREATED="1410148237856" ID="ID_944721551" MODIFIED="1410148253660" TEXT="Persistence layer"/>
</node>
<node CREATED="1410148205840" ID="ID_353682429" MODIFIED="1410148212603" TEXT="@Controller">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      stereotype for presentation layer (spring-mvc)
    </p>
  </body>
</html></richcontent>
<node CREATED="1410148267432" ID="ID_79349807" MODIFIED="1410148272468" TEXT="Presentation layer"/>
</node>
<node CREATED="1406086039490" ID="ID_1489742536" MODIFIED="1410148713139" TEXT="@PostConstruct">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Call on init bean. It works well in web project.
    </p>
  </body>
</html></richcontent>
<node CREATED="1410148282832" ID="ID_1145100947" MODIFIED="1410148285635" TEXT="init"/>
</node>
<node CREATED="1406086025218" ID="ID_1751502675" MODIFIED="1410148712616" TEXT="@PreDestroy">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Call on destroy bean. But, It not work in web project.?
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="messagebox_warning"/>
</node>
</node>
<node CREATED="1406085457604" ID="ID_700142899" MODIFIED="1406085463075" TEXT="Listener">
<node CREATED="1406088964901" FOLDED="true" ID="ID_1926062403" MODIFIED="1410148483641" TEXT="Context">
<node CREATED="1406085466462" ID="ID_1942516334" MODIFIED="1406085680784" TEXT="ContextRefreshedEvent">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      This event is published when the&#160;ApplicationContext&#160;is either initialized or refreshed. This can also be raised using the refresh() method on the&#160;ConfigurableApplicationContext&#160;interface.
    </p>
    <p>
      In my project, I 've seen that It raises two times.
    </p>
    <p>
      &#160;- First one is on &quot;Initializing Spring root WebApplicationContext&quot;
    </p>
    <p>
      &#160;- Second one is on &quot;Initializing Spring FrameworkServlet 'dispatcher'&quot;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1406086340535" ID="ID_973339613" MODIFIED="1410148444128" TEXT="ContextStartedEvent">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      This event is published when the ApplicationContext is started using the start() method on the ConfigurableApplicationContext interface. You can poll your database or you can restart any stopped application after receiving this event.
    </p>
    <p>
      
    </p>
    <p>
      Haven't called in web project. So &quot;<font color="rgb(0, 0, 0)" face="Consolas, Menlo, Monaco, Lucida Console, Liberation Mono, DejaVu Sans Mono, Bitstream Vera Sans Mono, Courier New, monospace, serif">ContextRefreshedEvent</font>&quot; is used instead of It.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1406087415816" ID="ID_200330749" MODIFIED="1406088292273" TEXT="ContextStoppedEvent">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      This event is published when the ApplicationContext is stopped using the stop() method on the ConfigurableApplicationContext interface. You can do required housekeep work after receiving this event.
    </p>
    <p>
      
    </p>
    <p>
      It is not worked in web app.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1406088012580" ID="ID_1644610603" MODIFIED="1406088474118" TEXT="ContextClosedEvent">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      This event is published when the ApplicationContext is closed using the close() method on the ConfigurableApplicationContext interface. A closed context reaches its end of life; it cannot be refreshed or restarted.
    </p>
    <p>
      
    </p>
    <p>
      It is not worked in web app.
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1406088990197" FOLDED="true" ID="ID_384178952" MODIFIED="1410148531153" TEXT="Web">
<node CREATED="1406089030989" ID="ID_163324143" MODIFIED="1406089639475" TEXT="ServletRequestHandledEvent">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      This is a web-specific event telling all beans that an HTTP request has been serviced.
    </p>
    <p>
      
    </p>
    <p>
      It works on any request in web app.
    </p>
    <p>
      
    </p>
    <p>
      It works before RequestHandledEvent.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1406088476024" ID="ID_690383381" MODIFIED="1406088510179" TEXT="RequestHandledEvent">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      This is a web-specific event telling all beans that an HTTP request has been serviced.
    </p>
    <p>
      
    </p>
    <p>
      It works on any request in web app.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1406089022861" ID="ID_504693649" MODIFIED="1406089023416" TEXT="PortletRequestHandledEvent"/>
</node>
<node CREATED="1406089012757" ID="ID_1896182004" MODIFIED="1410148323974" TEXT="Security">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Must add security meven depedency
    </p>
  </body>
</html></richcontent>
<node CREATED="1406090070173" ID="ID_1863708190" MODIFIED="1406090071232" TEXT="AuthenticationSuccessEvent"/>
<node CREATED="1406090141253" ID="ID_1441449146" MODIFIED="1406090142041" TEXT="AuthenticationSwitchUserEvent"/>
<node CREATED="1406090151085" ID="ID_1269245900" MODIFIED="1406090151673" TEXT="InteractiveAuthenticationSuccessEvent"/>
<node CREATED="1406090166525" ID="ID_262162489" MODIFIED="1406090167825" TEXT="AuthenticationFailureBadCredentialsEvent"/>
<node CREATED="1406090176005" ID="ID_1538552710" MODIFIED="1406090176800" TEXT="AuthenticationFailureCredentialsExpiredEvent"/>
<node CREATED="1406090180509" ID="ID_20836607" MODIFIED="1406090182344" TEXT="..."/>
</node>
</node>
</node>
<node COLOR="#1c801c" CREATED="1405478335311" ID="ID_995746560" MODIFIED="1410171547965" POSITION="left" STYLE="bubble" TEXT="JMS">
<edge COLOR="#1c801c" STYLE="sharp_bezier" WIDTH="8"/>
<font NAME="SansSerif" SIZE="12"/>
<icon BUILTIN="help"/>
</node>
<node COLOR="#1c801c" CREATED="1405478340525" FOLDED="true" ID="ID_163064924" MODIFIED="1425452250925" POSITION="left" STYLE="bubble" TEXT="Schedule">
<edge COLOR="#1c801c" STYLE="sharp_bezier" WIDTH="8"/>
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<icon BUILTIN="hourglass"/>
<node CREATED="1408532543325" ID="ID_1888290579" MODIFIED="1408532553168" TEXT="Xml">
<node CREATED="1408532588141" ID="ID_1717292410" MODIFIED="1408532589713" TEXT="task:scheduled-tasks">
<node CREATED="1408532599348" ID="ID_383500652" MODIFIED="1408532603072" TEXT="task:scheduled">
<node CREATED="1408532615429" ID="ID_946872956" MODIFIED="1408532616760" TEXT="ref"/>
<node CREATED="1408532617820" ID="ID_103617600" MODIFIED="1408532620664" TEXT="method"/>
<node CREATED="1408532623964" ID="ID_1981923885" MODIFIED="1410172575423" TEXT="cron">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      http://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html
    </p>
  </body>
</html></richcontent>
<linktarget COLOR="#b0b0b0" DESTINATION="ID_1981923885" ENDARROW="Default" ENDINCLINATION="301;0;" ID="Arrow_ID_699949365" SOURCE="ID_118634966" STARTARROW="None" STARTINCLINATION="301;0;"/>
<node CREATED="1410171915875" ID="ID_1918880293" MODIFIED="1410171997676" TEXT="0 0 * * * *">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      the top of every hour of every day
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1410171940291" ID="ID_1416975671" MODIFIED="1410172005636" TEXT="*/10 * * * * *">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      every ten seconds
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1410171949394" ID="ID_220123745" MODIFIED="1410172013179" TEXT="0 0 8-10 * * *">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      8, 9 and 10 o'clock of every day
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1410171956730" ID="ID_239566021" MODIFIED="1410172022266" TEXT="0 0/30 8-10 * * *">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      8:00, 8:30, 9:00, 9:30 and 10 o'clock every day
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1410171964946" ID="ID_1272441075" MODIFIED="1410172029675" TEXT="&quot;0 0 9-17 * * MON-FRI">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      on the hour nine-to-five weekdays
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1410171975330" ID="ID_1293128200" MODIFIED="1410172032244" TEXT="0 0 0 25 12 ?">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      every Christmas Day at midnight
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1408532638788" ID="ID_1764785449" MODIFIED="1408532904050" TEXT="fixed-delay">
<linktarget COLOR="#b0b0b0" DESTINATION="ID_1764785449" ENDARROW="Default" ENDINCLINATION="44;0;" ID="Arrow_ID_861935738" SOURCE="ID_140813777" STARTARROW="None" STARTINCLINATION="44;0;"/>
</node>
<node CREATED="1408532822858" ID="ID_1050516031" MODIFIED="1408532897643" TEXT="fixed-rate">
<linktarget COLOR="#b0b0b0" DESTINATION="ID_1050516031" ENDARROW="Default" ENDINCLINATION="24;0;" ID="Arrow_ID_86038230" SOURCE="ID_140813777" STARTARROW="None" STARTINCLINATION="24;0;"/>
</node>
<node CREATED="1408532648004" ID="ID_140813777" MODIFIED="1408532904051" TEXT="initial-delay">
<arrowlink DESTINATION="ID_1050516031" ENDARROW="Default" ENDINCLINATION="24;0;" ID="Arrow_ID_86038230" STARTARROW="None" STARTINCLINATION="24;0;"/>
<arrowlink DESTINATION="ID_1764785449" ENDARROW="Default" ENDINCLINATION="44;0;" ID="Arrow_ID_861935738" STARTARROW="None" STARTINCLINATION="44;0;"/>
</node>
</node>
<node CREATED="1408532946170" ID="ID_573744794" MODIFIED="1408532998540" TEXT="scheduler">
<arrowlink DESTINATION="ID_1437576190" ENDARROW="Default" ENDINCLINATION="94;0;" ID="Arrow_ID_877130090" STARTARROW="None" STARTINCLINATION="94;0;"/>
</node>
</node>
<node CREATED="1408532985169" ID="ID_1920899810" MODIFIED="1408532986533" TEXT="task:scheduler">
<node CREATED="1408532991641" ID="ID_1437576190" MODIFIED="1408532998539" TEXT="id">
<linktarget COLOR="#b0b0b0" DESTINATION="ID_1437576190" ENDARROW="Default" ENDINCLINATION="94;0;" ID="Arrow_ID_877130090" SOURCE="ID_573744794" STARTARROW="None" STARTINCLINATION="94;0;"/>
</node>
<node CREATED="1408533008361" ID="ID_693605906" MODIFIED="1408533009381" TEXT="pool-size"/>
</node>
</node>
<node CREATED="1408532554165" ID="ID_415404763" MODIFIED="1408532558160" TEXT="Annotation">
<node CREATED="1410172269952" ID="ID_355615127" MODIFIED="1410172271628" TEXT="@Scheduled">
<node CREATED="1410172596358" ID="ID_1276996273" MODIFIED="1410172626488" TEXT="initialDelay"/>
<node CREATED="1410172664085" ID="ID_1761360465" MODIFIED="1410172712205" TEXT="initialDelayString">
<icon BUILTIN="help"/>
</node>
<node CREATED="1410172550462" ID="ID_1921359083" MODIFIED="1410172551610" TEXT="fixedDelay"/>
<node CREATED="1410172674621" ID="ID_1062654162" MODIFIED="1410172714125" TEXT="fixedDelayString">
<icon BUILTIN="help"/>
</node>
<node CREATED="1410172559070" ID="ID_355702186" MODIFIED="1410172559946" TEXT="fixedRate"/>
<node CREATED="1410172706701" ID="ID_1551285543" MODIFIED="1410172716261" TEXT="fixedRateString">
<icon BUILTIN="help"/>
</node>
<node CREATED="1410172566846" ID="ID_118634966" MODIFIED="1410172575423" TEXT="cron">
<arrowlink DESTINATION="ID_1981923885" ENDARROW="Default" ENDINCLINATION="301;0;" ID="Arrow_ID_699949365" STARTARROW="None" STARTINCLINATION="301;0;"/>
</node>
<node CREATED="1410172630262" ID="ID_1179062434" MODIFIED="1410172722278" TEXT="zone">
<icon BUILTIN="help"/>
</node>
</node>
<node CREATED="1410172744997" ID="ID_1632726549" MODIFIED="1410172745705" TEXT="@Async">
<node CREATED="1410173589183" ID="ID_44984580" MODIFIED="1410173636567" TEXT="value">
<arrowlink DESTINATION="ID_1631424523" ENDARROW="Default" ENDINCLINATION="103;0;" ID="Arrow_ID_1477550798" STARTARROW="None" STARTINCLINATION="103;0;"/>
</node>
<node CREATED="1410173568511" ID="ID_1004716341" MODIFIED="1410173625482" TEXT="task:executor">
<node CREATED="1410173631447" ID="ID_1631424523" MODIFIED="1410173636567" TEXT="id">
<linktarget COLOR="#b0b0b0" DESTINATION="ID_1631424523" ENDARROW="Default" ENDINCLINATION="103;0;" ID="Arrow_ID_1477550798" SOURCE="ID_44984580" STARTARROW="None" STARTINCLINATION="103;0;"/>
</node>
<node CREATED="1410173570927" ID="ID_1384144606" MODIFIED="1410173614994" TEXT="pool-size"/>
<node CREATED="1410173686918" ID="ID_867828941" MODIFIED="1410173688258" TEXT="rejection-policy"/>
</node>
</node>
</node>
</node>
<node COLOR="#1c801c" CREATED="1405478350216" FOLDED="true" ID="ID_1815266670" MODIFIED="1408952388877" POSITION="left" STYLE="bubble" TEXT="Security">
<edge COLOR="#1c801c" STYLE="sharp_bezier" WIDTH="8"/>
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<icon BUILTIN="password"/>
<node CREATED="1406649991882" ID="ID_365602480" MODIFIED="1406649996861" TEXT="config">
<node CREATED="1406649998265" ID="ID_1765947065" MODIFIED="1406650058240" TEXT="web.xml">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &lt;filter&gt;
    </p>
    <p>
      &#160;&#160;&lt;filter-name&gt;springSecurityFilterChain&lt;/filter-name&gt;
    </p>
    <p>
      &#160; &lt;filter-class&gt;org.springframework.web.filter.DelegatingFilterProxy&lt;/filter-class&gt;
    </p>
    <p>
      &lt;/filter&gt;
    </p>
    <p>
      &lt;filter-mapping&gt;
    </p>
    <p>
      &#160;&#160;&lt;filter-name&gt;springSecurityFilterChain&lt;/filter-name&gt;
    </p>
    <p>
      &#160;&#160;&lt;url-pattern&gt;/*&lt;/url-pattern&gt;
    </p>
    <p>
      &lt;/filter-mapping&gt;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1406650088470" FOLDED="true" ID="ID_752043156" MODIFIED="1407132819728" TEXT="spring-security.xml">
<node CREATED="1406650579608" ID="ID_875740387" MODIFIED="1407132748043" TEXT="http">
<node CREATED="1406650607481" ID="ID_322261329" MODIFIED="1406650636963" TEXT="http-basic">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &lt;http auto-config='true'&gt;
    </p>
    <p>
      &#160;&#160;&lt;intercept-url pattern=&quot;/**&quot; access=&quot;ROLE_USER&quot; /&gt;
    </p>
    <p>
      &#160;&#160;&lt;http-basic /&gt;
    </p>
    <p>
      &lt;/http&gt;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1406778746204" ID="ID_275207012" MODIFIED="1406778760540" TEXT="logout">
<node CREATED="1406778772024" ID="ID_1720373748" MODIFIED="1406778773156" TEXT="logout-success-url"/>
<node CREATED="1406778786664" ID="ID_82458078" MODIFIED="1406778787269" TEXT="delete-cookies"/>
<node CREATED="1406778801864" ID="ID_948530152" MODIFIED="1406778871437" TEXT="invalidate-session">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node CREATED="1406778862312" ID="ID_1292333929" MODIFIED="1406778920641" TEXT="true">
<font NAME="SansSerif" SIZE="12"/>
</node>
<node COLOR="#ff0000" CREATED="1406778811079" ID="ID_795010824" MODIFIED="1406779117252" STYLE="bubble" TEXT="false">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Must <b><font color="#ff0000">false</font></b>&#160;if you want to setup the HttpSessionAttribueListner
    </p>
    <p>
      so that you won't get exception:
    </p>
    <p>
      <i><b>org.apache.catalina.session.StandardSession removeAttributeInternal </b></i>
    </p>
    <p>
      <i><b>SEVERE: Session attribute event listener threw exception </b></i>
    </p>
    <p>
      <i><b>java.lang.IllegalStateException: getAttribute: Session already invalidated</b></i>
    </p>
  </body>
</html></richcontent>
<edge COLOR="#1c801c" STYLE="sharp_bezier" WIDTH="8"/>
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
</node>
</node>
</node>
<node CREATED="1406778895031" ID="ID_691289594" MODIFIED="1406778965273" TEXT="use-expressions">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node COLOR="#ff0000" CREATED="1406778961246" ID="ID_1939563350" MODIFIED="1406778975452" STYLE="bubble" TEXT="true">
<edge COLOR="#1c801c" STYLE="sharp_bezier" WIDTH="8"/>
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
</node>
</node>
<node CREATED="1407123385588" ID="ID_414881251" MODIFIED="1407123426996" TEXT="intercept-url">
<node CREATED="1407123431065" ID="ID_903327383" MODIFIED="1407123440885" TEXT="pattern">
<node CREATED="1407123460033" ID="ID_719676799" MODIFIED="1407123466253" TEXT="Ex: /admin/cpanel"/>
</node>
<node CREATED="1407123454625" FOLDED="true" ID="ID_893341721" MODIFIED="1407123816350" TEXT="access">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      http://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html
    </p>
  </body>
</html></richcontent>
<node CREATED="1407123559457" ID="ID_1599367184" MODIFIED="1407123802698" TEXT="hasRole([role])">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
</node>
<node CREATED="1407123575504" ID="ID_587366825" MODIFIED="1407123803577" TEXT="hasAnyRole([role1, role2])">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
</node>
<node CREATED="1407123686943" ID="ID_801935864" MODIFIED="1407123687523" TEXT="principal"/>
<node CREATED="1407123695359" ID="ID_1794455203" MODIFIED="1407123695803" TEXT="authentication"/>
<node CREATED="1407123489377" ID="ID_1645109163" MODIFIED="1407123799249" TEXT="permitAll">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
</node>
<node CREATED="1407123661951" ID="ID_1808726292" MODIFIED="1407123800305" TEXT="denyAll">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
</node>
<node CREATED="1407123745759" ID="ID_210404409" MODIFIED="1407123746219" TEXT="isAnonymous()"/>
<node CREATED="1407123757743" ID="ID_1009066327" MODIFIED="1407123758346" TEXT="isRememberMe()"/>
<node CREATED="1407123774582" ID="ID_1589560251" MODIFIED="1407123806969" TEXT="isAuthenticated()">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
</node>
<node CREATED="1407123793982" ID="ID_216331612" MODIFIED="1407123794578" TEXT="isFullyAuthenticated()"/>
<node CREATED="1407123394186" ID="ID_1471036656" MODIFIED="1407123809081" TEXT="and hasIpAddress(&apos;127.0.0.0/24&apos;)">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      http://forum.spring.io/forum/spring-projects/security/95303-how-to-use-hasipaddress
    </p>
  </body>
</html></richcontent>
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
</node>
</node>
</node>
<node CREATED="1407124263099" ID="ID_1467452031" MODIFIED="1407124303887" TEXT="port-mappings">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      http://stackoverflow.com/questions/8915701/spring-security-3-setting-up-tomcat-6-for-ssl
    </p>
  </body>
</html></richcontent>
<node CREATED="1407124269483" ID="ID_713829969" MODIFIED="1407124271983" TEXT="http"/>
<node CREATED="1407124272459" ID="ID_788043301" MODIFIED="1407124278495" TEXT="https"/>
</node>
</node>
<node CREATED="1406650175557" ID="ID_1629589916" MODIFIED="1407124987556" TEXT="authentication-manager">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &lt;authentication-manager&gt;
    </p>
    <p>
      &#160;&#160;&lt;authentication-provider&gt;
    </p>
    <p>
      &#160;&#160;&#160;&#160;&lt;user-service&gt;
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;&lt;user name=&quot;admin&quot; password=&quot;admin&quot; authorities=&quot;ROLE_USER, ROLE_ADMIN&quot; /&gt;
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;&lt;user name=&quot;user&quot; password=&quot;user&quot; authorities=&quot;ROLE_USER&quot; /&gt;
    </p>
    <p>
      &#160;&#160;&#160;&#160;&lt;/user-service&gt;
    </p>
    <p>
      &#160;&#160;&lt;/authentication-provider&gt;
    </p>
    <p>
      &lt;/authentication-manager&gt;
    </p>
  </body>
</html></richcontent>
<node CREATED="1406783143993" ID="ID_1297289900" MODIFIED="1406783205047" TEXT="password-encoder">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &lt;password-encoder hash=&quot;sha&quot; /&gt;
    </p>
    <p>
      or &lt;password-encoder hash=&quot;sha-256&quot; /&gt;
    </p>
    <p>
      or &lt;password-encoder hash=&quot;md5?&quot; /&gt;
    </p>
  </body>
</html></richcontent>
<node CREATED="1406783206281" ID="ID_1727086196" MODIFIED="1406783208661" TEXT="sha"/>
<node CREATED="1406783209145" ID="ID_1952699343" MODIFIED="1406783214021" TEXT="sha-256"/>
<node CREATED="1406783218641" ID="ID_376118577" MODIFIED="1406783221509" TEXT="..."/>
<node CREATED="1406783222305" ID="ID_464019692" MODIFIED="1406783224316" TEXT="md5"/>
<node CREATED="1406783257504" ID="ID_256142256" MODIFIED="1406783377735" TEXT="ref">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Reference to the bean name:
    </p>
    <p>
      &lt;password-encoder ref=&quot;customPasswordEncoder&quot; /&gt;
    </p>
    <p>
      
    </p>
    <p>
      @Component
    </p>
    <p>
      public class CustomPasswordEncoder implements PasswordEncoder {
    </p>
    <p>
      
    </p>
    <p>
      &#160;&#160;@Override
    </p>
    <p>
      &#160;&#160;public String encode(CharSequence arg0) {
    </p>
    <p>
      &#160;&#160;&#160;&#160;return null;
    </p>
    <p>
      &#160;&#160;}
    </p>
    <p>
      
    </p>
    <p>
      &#160;&#160;@Override
    </p>
    <p>
      &#160;&#160;public boolean matches(CharSequence inputPassword, String dbPassword) {
    </p>
    <p>
      &#160;&#160;&#160;&#160;if (StringUtils.isNotEmpty(inputPassword.toString()) &amp;&amp; StringUtils.isNotEmpty(dbPassword)) {
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;return MD5.encode(&quot;Spring&quot; + inputPassword).equals(dbPassword);
    </p>
    <p>
      &#160;&#160;&#160;&#160;}
    </p>
    <p>
      &#160;&#160;&#160;&#160;return false;
    </p>
    <p>
      &#160;&#160;}
    </p>
    <p>
      }
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1406783240368" ID="ID_1195566664" MODIFIED="1406783528989" TEXT="jdbc-user-service">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &lt;jdbc-user-service <b>data-source-ref</b>=&quot;dataSource&quot;
    </p>
    <p>
      &#160;&#160;<b>users-by-username-query</b>=&quot;SELECT UserName AS username, Password AS password, Active AS enabled FROM tblUser WHERE UserName=?&quot;
    </p>
    <p>
      &#160;&#160;<b>authorities-by-username-query</b>=&quot;SELECT u.UserName AS username, p.Code AS authority FROM tblUser u, tblPermission p, tblGroup g
    </p>
    <p>
      &#160;&#160;, tblGroupPermission gp WHERE u.GroupID = g.ID AND gp.GroupID = p.ID AND gp.PermissionID = p.ID AND u.UserName=?&quot; /&gt;
    </p>
  </body>
</html></richcontent>
<node CREATED="1406783473711" ID="ID_1802549457" MODIFIED="1406783507403" TEXT="data-source-ref"/>
<node CREATED="1406783517406" ID="ID_1815075188" MODIFIED="1406783518138" TEXT="users-by-username-query"/>
<node CREATED="1406783528974" ID="ID_1363312881" MODIFIED="1406783530583" TEXT="authorities-by-username-query"/>
</node>
<node CREATED="1406799625078" ID="ID_830757166" MODIFIED="1406799626858" TEXT="user-service-ref">
<node CREATED="1406799637654" ID="ID_1265903139" MODIFIED="1406800595344" TEXT="config">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &lt;authentication-manager alias=&quot;authenticationManager&quot;&gt;
    </p>
    <p>
      &#160;&#160;&lt;authentication-provider user-service-ref=&quot;userDetailsService&quot;&gt;
    </p>
    <p>
      &#160;&#160;&#160;&#160;&lt;password-encoder ref=&quot;customPasswordEncoder&quot; /&gt;
    </p>
    <p>
      &#160;&#160;&lt;/authentication-provider&gt;
    </p>
    <p>
      &lt;/authentication-manager&gt;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1406799640830" ID="ID_842311023" MODIFIED="1406800624355" TEXT="bean">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      @Component(value = &quot;userDetailsService&quot;)
    </p>
    <p>
      public class UserDetailsServiceImpl implements UserDetailsService { . . . }
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1407124991126" ID="ID_1135554086" MODIFIED="1407125011017" TEXT="global-method-security">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      http://www.studytrails.com/frameworks/spring/spring-security-method-level.jsp
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="help"/>
</node>
<node CREATED="1407132796510" MODIFIED="1407132796510" TEXT="Method security: ??"/>
<node CREATED="1407132796510" LINK="http://www.studytrails.com/frameworks/spring/spring-security-method-level.jsp" MODIFIED="1407132796510" TEXT="--&gt; http://www.studytrails.com/frameworks/spring/spring-security-method-level.jsp"/>
</node>
<node CREATED="1406788632579" ID="ID_127970930" MODIFIED="1406788643575" TEXT="form">
<node CREATED="1406788644651" ID="ID_894283556" MODIFIED="1406788647703" TEXT="login">
<node CREATED="1406788686554" ID="ID_1354697800" MODIFIED="1406788698404" TEXT="action">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &lt;c:url value='/j_spring_security_check'/&gt;
    </p>
  </body>
</html></richcontent>
<node CREATED="1406788739202" ID="ID_978372223" MODIFIED="1406788740254" TEXT="j_spring_security_check"/>
</node>
<node CREATED="1406788701746" ID="ID_1913089259" MODIFIED="1406788709262" TEXT="post method"/>
</node>
<node CREATED="1406788648099" ID="ID_564094022" MODIFIED="1406788650118" TEXT="logout">
<node CREATED="1406788755402" ID="ID_1467825924" MODIFIED="1406788763109" TEXT="action">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &lt;c:url value='/j_spring_security_logout'/&gt;
    </p>
  </body>
</html></richcontent>
<node CREATED="1406788763098" ID="ID_314469524" MODIFIED="1406788770838" TEXT="j_spring_security_logout"/>
</node>
</node>
</node>
</node>
</node>
<node CREATED="1407132534369" ID="ID_1056702825" MODIFIED="1425537917402" POSITION="left" TEXT="AOP">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      http://docs.spring.io/spring/docs/3.0.5.RELEASE/reference/aop.html
    </p>
  </body>
</html>
</richcontent>
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<icon BUILTIN="broken-line"/>
<node CREATED="1407383991197" ID="ID_830743791" MODIFIED="1407384526726" TEXT="@Aspect">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <b><i>Definition: </i></b><i>A modularization of a concern that cuts across multiple classes. Transaction management is a good example of a crosscutting concern in enterprise Java applications. </i>
    </p>
    <p>
      
    </p>
    <p>
      <i>@Component </i>
    </p>
    <p>
      <b><i>@Aspect</i></b>
    </p>
    <p>
      <i>public class CustomAOP { ... }</i>
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1407384643449" ID="ID_295518512" MODIFIED="1407385030577" TEXT="Join point">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <b><i>Definition:</i></b>&#160;a point during the execution of a program, such as the execution of a <b><font color="#ff0000"><i>method</i></font></b>&#160;or the handling of an exception. In Spring AOP, a join point always represents a method execution.
    </p>
    <p>
      
    </p>
    <p>
      @RequestMapping(value = &quot;/users&quot;, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    </p>
    <p>
      @CustomAnnotation(&quot;Test for AOP annotation pointcut&quot;)
    </p>
    <p>
      public @ResponseBody Map&lt;String, Object&gt; <b><font color="#ff0000"><i>users</i></font></b>(HttpServletRequest request, HttpServletResponse response) { ... }
    </p>
  </body>
</html></richcontent>
<node CREATED="1407385064302" ID="ID_1387268388" MODIFIED="1407385075170" TEXT="Ex: AdminController.users(..)"/>
</node>
<node CREATED="1407140362732" ID="ID_800580456" MODIFIED="1407725474620" TEXT="pointcut">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <b><i>Definition:</i></b>&#160;a predicate that matches join points. Advice is associated with a pointcut expression and runs at any join point matched by the pointcut (for example, the execution of a method with a certain name). The concept of join points as matched by pointcut expressions is central to AOP, and Spring uses the AspectJ pointcut expression language by default.
    </p>
    <p>
      
    </p>
    <p>
      @Pointcut(&quot;anyPublicOperation() &amp;&amp; inTrading()&quot;)
    </p>
    <p>
      private void tradingOperation() {}
    </p>
  </body>
</html></richcontent>
<node CREATED="1407140391395" ID="ID_1471052244" MODIFIED="1407383862634" TEXT="expressions">
<node CREATED="1407383517592" ID="ID_1272566952" MODIFIED="1407383713018" TEXT="args">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      @Before(&quot;execution(* javaz.spring.mvc.controller.*.*(..)) &amp;&amp; <b>args</b>(<b><font color="#ff0000"><i>request</i></font></b>,..)&quot;)
    </p>
    <p>
      CustomAOP.before(HttpServletRequest <b><font color="#ff0000"><i>request</i></font></b>) { ... }
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1407383596360" ID="ID_298916190" MODIFIED="1407383790362" TEXT="@annotation">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      @Before(&quot;execution(* javaz.spring.mvc.controller.*.*(..)) &amp;&amp; <b>@annotation</b>(<font color="#ff0000">cusAnnotation</font>)&quot;)
    </p>
    <p>
      public void before(<b><font color="#b15252"><i>CustomAnnotation</i></font></b>&#160;<font color="#ff0000">cusAnnotation</font>) { ... }
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1407383820607" ID="ID_1730818058" MODIFIED="1407383822258" TEXT="target"/>
<node CREATED="1407383834799" ID="ID_1650523639" MODIFIED="1407383845067" TEXT="Many exp">
<node CREATED="1407140432619" ID="ID_642300709" MODIFIED="1407383659377" TEXT="&amp;&amp;">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      execution(* javaz.spring.mvc.controller.*.*(..)) <b>&amp;&amp;</b>&#160;args(request,..)
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1407140435122" ID="ID_653112070" MODIFIED="1407140436575" TEXT="||"/>
<node CREATED="1407140437011" ID="ID_1774742470" MODIFIED="1407140439070" TEXT="!"/>
</node>
</node>
</node>
<node CREATED="1407204635182" ID="ID_1610149888" MODIFIED="1407385636777" TEXT="advice">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <b><i>Definition:</i></b>&#160;action taken by an aspect at a particular join point. Different types of advice include &quot;around,&quot; &quot;before&quot; and &quot;after&quot; advice. (Advice types are discussed below.) Many AOP frameworks, including Spring, model an advice as an interceptor, maintaining a chain of interceptors around the join point.
    </p>
  </body>
</html></richcontent>
<node CREATED="1407204654019" ID="ID_97144417" MODIFIED="1407385778280" TEXT="@Before">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <b>@Before</b>(&quot;execution(* javaz.spring.mvc.controller.*.*(..))&quot;)
    </p>
    <p>
      public void LoggingAspect.logBefore(JoinPoint joinPoint) {...}
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1407204709427" ID="ID_1626944508" MODIFIED="1407385773473" TEXT="@After">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <b>@After</b>(&quot;execution(* javaz.spring.mvc.controller.*.*(..))&quot;)
    </p>
    <p>
      public void LoggingAspect.logAfter(JoinPoint joinPoint) {...}
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1407204724595" ID="ID_543838561" MODIFIED="1407385840257" TEXT="@AfterReturning">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <b>@AfterReturning</b>(pointcut = &quot;execution(* javaz.spring.mvc.controller.*.*(..))&quot;, returning = &quot;<b><font color="#ff0000"><i>result</i></font></b>&quot;)
    </p>
    <p>
      public void LoggingAspect.logAfterReturning(JoinPoint joinPoint, Object <b><font color="#ff0000"><i>result</i></font></b>) {...}
    </p>
  </body>
</html></richcontent>
<node CREATED="1407206222968" ID="ID_1600486101" MODIFIED="1407206224108" TEXT="returning"/>
</node>
<node CREATED="1407204760075" ID="ID_100532218" MODIFIED="1407385895032" TEXT="@AfterThrowing">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <b>@AfterThrowing</b>(pointcut = &quot;execution(* javaz.spring.mvc.controller.*.*(..))&quot;, throwing = &quot;<b><font color="#ff0000"><i>error</i></font></b>&quot;)
    </p>
    <p>
      public void logAfterThrowing(JoinPoint joinPoint, Throwable <b><font color="#ff0000"><i>error</i></font></b>) {...}
    </p>
  </body>
</html></richcontent>
<node CREATED="1407206231104" ID="ID_652168775" MODIFIED="1407206232028" TEXT="throwing"/>
</node>
<node CREATED="1407204789722" ID="ID_926564731" MODIFIED="1407387110150" TEXT="@Around">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <b>@Around</b>(&quot;execution(* javaz.spring.mvc.controller.*.*(..))&quot;)
    </p>
    <p>
      public Object LoggingAspect.logAround(ProceedingJoinPoint joinPoint) throws Throwable {...}
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node COLOR="#1c801c" CREATED="1405478377690" ID="ID_606548515" MODIFIED="1406651073494" POSITION="left" STYLE="bubble" TEXT="ORM">
<edge COLOR="#1c801c" STYLE="sharp_bezier" WIDTH="8"/>
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<icon BUILTIN="clanbomber"/>
<node BACKGROUND_COLOR="#bcac79" COLOR="#59666c" CREATED="1408084603748" ID="ID_582781487" MODIFIED="1408085460961" STYLE="bubble" TEXT="Hibernate">
<edge COLOR="#59666c" STYLE="sharp_bezier" WIDTH="8"/>
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
</node>
<node BACKGROUND_COLOR="#59666c" COLOR="#bcac79" CREATED="1408084611363" ID="ID_1331436088" MODIFIED="1408085747417" STYLE="bubble" TEXT="JPA">
<edge COLOR="#bcac79" STYLE="sharp_bezier" WIDTH="8"/>
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
</node>
</node>
<node COLOR="#1c801c" CREATED="1405478331113" ID="ID_562226432" MODIFIED="1411013969520" POSITION="right" STYLE="bubble" TEXT="MVC">
<edge COLOR="#1c801c" STYLE="sharp_bezier" WIDTH="8"/>
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<icon BUILTIN="go"/>
<node CREATED="1406107715882" ID="ID_1829078718" MODIFIED="1406107718702" TEXT="Config">
<node CREATED="1406108677260" ID="ID_82143715" MODIFIED="1406108680687" TEXT="web.xml">
<node CREATED="1406108708508" ID="ID_303300702" MODIFIED="1406108861120" TEXT="listener">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &lt;listener&gt;
    </p>
    <p>
      &#160;&#160;&lt;listener-class&gt;org.springframework.web.context.ContextLoaderListener&lt;/listener-class&gt;
    </p>
    <p>
      &lt;/listener&gt;
    </p>
  </body>
</html></richcontent>
<node CREATED="1410148573830" ID="ID_181951669" MODIFIED="1410148574850" TEXT="ContextLoaderListener"/>
</node>
<node CREATED="1406108791435" ID="ID_526619517" MODIFIED="1410148616001" TEXT="servlet: dispatcher">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &lt;servlet&gt;
    </p>
    <p>
      &#160;&#160;&lt;servlet-name&gt;dispatcher&lt;/servlet-name&gt;
    </p>
    <p>
      &#160;&#160;&lt;servlet-class&gt;org.springframework.web.servlet.DispatcherServlet&lt;/servlet-class&gt;
    </p>
    <p>
      &#160;&#160;&lt;load-on-startup&gt;1&lt;/load-on-startup&gt;
    </p>
    <p>
      &lt;/servlet&gt;
    </p>
    <p>
      &lt;servlet-mapping&gt;
    </p>
    <p>
      &#160;&#160;&lt;servlet-name&gt;dispatcher&lt;/servlet-name&gt;
    </p>
    <p>
      &#160;&#160;&lt;url-pattern&gt;/&lt;/url-pattern&gt;
    </p>
    <p>
      &lt;/servlet-mapping&gt;
    </p>
  </body>
</html></richcontent>
<node CREATED="1410148597485" ID="ID_681520492" MODIFIED="1410148599481" TEXT="DispatcherServlet"/>
</node>
</node>
<node CREATED="1406108897778" ID="ID_676482992" MODIFIED="1410149848677" TEXT="applicationContext.xml">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      http://stackoverflow.com/questions/7414794/difference-between-contextannotation-config-vs-contextcomponent-scan
    </p>
  </body>
</html></richcontent>
<node CREATED="1406108915833" ID="ID_1888993274" MODIFIED="1410149693159" TEXT="annotation-config">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      It used to active annotations in beans already registered instead of using XML configuration to register in the application context.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1406108924762" ID="ID_1559437749" MODIFIED="1410149824358" TEXT="component-scan">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Alow the application context scan the registered beans in the indicated packages.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1406108936330" ID="ID_709522949" MODIFIED="1410149988844" TEXT="annotation-driven">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &lt;mvc:annotation-driven /&gt; declares explicit support for annotation-driven MVC controllers (i.e. @RequestMapping, @Controller, although support for those is the default behaviour), as well as adding support for declrative validation via @Valid and message body marshalling with @RequestBody/ResponseBody
    </p>
    <p>
      
    </p>
    <p>
      http://stackoverflow.com/questions/3977973/whats-the-difference-between-mvcannotation-driven-and-contextannotation
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1406108951753" ID="ID_1809309639" MODIFIED="1406108952253" TEXT="import">
<node CREATED="1406108968737" ID="ID_1521687404" MODIFIED="1406108971509" TEXT="hibernate"/>
<node CREATED="1406108971785" ID="ID_1017485873" MODIFIED="1406108974606" TEXT="aop"/>
</node>
<node CREATED="1406108965194" ID="ID_948555999" MODIFIED="1410150463937" TEXT="messageSource">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      http://www.mkyong.com/spring/spring-how-to-access-messagesource-in-bean-messagesourceaware/
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="help"/>
</node>
</node>
<node CREATED="1406108993425" ID="ID_535640707" MODIFIED="1406108993973" TEXT="dispatcher-servlet.xml">
<node CREATED="1406109016449" ID="ID_1915884628" MODIFIED="1406109071342" TEXT="view mapping">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &lt;bean class=&quot;org.springframework.web.servlet.view.InternalResourceViewResolver&quot;&gt;
    </p>
    <p>
      &#160;&#160;&lt;property name=&quot;prefix&quot;&gt;
    </p>
    <p>
      &#160;&#160;&#160;&#160;&lt;value&gt;/views/&lt;/value&gt;
    </p>
    <p>
      &#160;&#160;&lt;/property&gt;
    </p>
    <p>
      &#160;&#160;&lt;property name=&quot;suffix&quot;&gt;
    </p>
    <p>
      &#160;&#160;&#160;&#160;&lt;value&gt;.jsp&lt;/value&gt;
    </p>
    <p>
      &#160;&#160;&lt;/property&gt;
    </p>
    <p>
      &lt;/bean&gt;
    </p>
  </body>
</html></richcontent>
<node CREATED="1410150489768" ID="ID_818912409" MODIFIED="1410150490836" TEXT="InternalResourceViewResolver"/>
</node>
<node CREATED="1406109022241" ID="ID_1989232966" MODIFIED="1406109083747" TEXT="resource">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &lt;mvc:resources mapping=&quot;/resources/**&quot; location=&quot;/resources/&quot; /&gt;
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1406107719114" ID="ID_1251532346" MODIFIED="1406107721270" TEXT="Bean">
<node CREATED="1406107724322" ID="ID_489902926" MODIFIED="1406107725326" TEXT="@Controller"/>
<node CREATED="1406107765970" ID="ID_1181870393" MODIFIED="1406107767318" TEXT="@RequestMapping">
<node CREATED="1406107796146" ID="ID_337244009" MODIFIED="1406107799830" TEXT="value"/>
</node>
<node CREATED="1406107832777" ID="ID_1192259126" MODIFIED="1406107834957" TEXT="action">
<node CREATED="1406107835857" ID="ID_1188533442" MODIFIED="1406107837285" TEXT="@RequestMapping">
<node CREATED="1406107935529" ID="ID_1179119882" MODIFIED="1406107937084" TEXT="value">
<node CREATED="1406108455589" ID="ID_1107044542" MODIFIED="1406108468397" TEXT="required">
<font BOLD="true" ITALIC="true" NAME="SansSerif" SIZE="12"/>
</node>
</node>
<node CREATED="1406107953048" ID="ID_38216053" MODIFIED="1406107954331" TEXT="method">
<node CREATED="1406108135879" ID="ID_1430197247" MODIFIED="1406108159938" TEXT="RequestMethod">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
    </p>
  </body>
</html></richcontent>
<node CREATED="1406108442653" ID="ID_1303118292" MODIFIED="1406108486255" TEXT="GET is default">
<font BOLD="true" ITALIC="true" NAME="SansSerif" SIZE="12"/>
</node>
</node>
</node>
<node CREATED="1406107943281" ID="ID_863157334" MODIFIED="1406107944093" TEXT="produces">
<node CREATED="1406108087408" ID="ID_825897934" MODIFIED="1410151225112" TEXT="MediaType">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      ALL = valueOf(ALL_VALUE);
    </p>
    <p>
      APPLICATION_ATOM_XML = valueOf(APPLICATION_ATOM_XML_VALUE);
    </p>
    <p>
      APPLICATION_FORM_URLENCODED = valueOf(APPLICATION_FORM_URLENCODED_VALUE);
    </p>
    <p>
      APPLICATION_JSON = valueOf(APPLICATION_JSON_VALUE);
    </p>
    <p>
      APPLICATION_OCTET_STREAM = valueOf(APPLICATION_OCTET_STREAM_VALUE);
    </p>
    <p>
      APPLICATION_XHTML_XML = valueOf(APPLICATION_XHTML_XML_VALUE);
    </p>
    <p>
      APPLICATION_XML = valueOf(APPLICATION_XML_VALUE);
    </p>
    <p>
      IMAGE_GIF = valueOf(IMAGE_GIF_VALUE);
    </p>
    <p>
      IMAGE_JPEG = valueOf(IMAGE_JPEG_VALUE);
    </p>
    <p>
      IMAGE_PNG = valueOf(IMAGE_PNG_VALUE);
    </p>
    <p>
      MULTIPART_FORM_DATA = valueOf(MULTIPART_FORM_DATA_VALUE);
    </p>
    <p>
      TEXT_HTML = valueOf(TEXT_HTML_VALUE);
    </p>
    <p>
      TEXT_PLAIN = valueOf(TEXT_PLAIN_VALUE);
    </p>
    <p>
      TEXT_XML = valueOf(TEXT_XML_VALUE);
    </p>
  </body>
</html></richcontent>
<arrowlink DESTINATION="ID_1317825920" ENDARROW="Default" ENDINCLINATION="100;0;" ID="Arrow_ID_1948401731" STARTARROW="None" STARTINCLINATION="100;0;"/>
<node CREATED="1410151335106" ID="ID_1006939458" MODIFIED="1410151337564" TEXT="MULTIPART_FORM_DATA"/>
<node CREATED="1410151206211" ID="ID_471083362" MODIFIED="1410151207134" TEXT="APPLICATION_JSON"/>
<node CREATED="1410151248731" ID="ID_1225979367" MODIFIED="1410151250487" TEXT="APPLICATION_OCTET_STREAM"/>
<node CREATED="1410151258675" ID="ID_533903772" MODIFIED="1410151259263" TEXT="APPLICATION_XML"/>
<node CREATED="1410151313514" ID="ID_1897877279" MODIFIED="1410151314054" TEXT="APPLICATION_XHTML_XML"/>
<node CREATED="1410151322730" ID="ID_1731331614" MODIFIED="1410151323454" TEXT="APPLICATION_ATOM_XML"/>
<node CREATED="1410151226331" ID="ID_1792162664" MODIFIED="1410151228039" TEXT="TEXT_PLAIN"/>
<node CREATED="1410151277035" ID="ID_414129693" MODIFIED="1410151277911" TEXT="TEXT_XML"/>
<node CREATED="1410151287563" ID="ID_1434324474" MODIFIED="1410151288166" TEXT="TEXT_HTML"/>
<node CREATED="1410151361594" ID="ID_1943246750" MODIFIED="1410151363222" TEXT="IMAGE_GIF"/>
<node CREATED="1410151365082" ID="ID_1394719517" MODIFIED="1410151378142" TEXT="IMAGE_JPEG"/>
<node CREATED="1410151378578" ID="ID_377306383" MODIFIED="1410151382110" TEXT="IMAGE_PNG"/>
</node>
</node>
<node CREATED="1406108003440" ID="ID_1317825920" MODIFIED="1406108102208" TEXT="consumes">
<linktarget COLOR="#b0b0b0" DESTINATION="ID_1317825920" ENDARROW="Default" ENDINCLINATION="100;0;" ID="Arrow_ID_1948401731" SOURCE="ID_825897934" STARTARROW="None" STARTINCLINATION="100;0;"/>
</node>
<node CREATED="1410171424574" ID="ID_1285256013" MODIFIED="1410171440126" TEXT="More">
<icon BUILTIN="help"/>
<icon BUILTIN="help"/>
</node>
</node>
<node CREATED="1406108415949" ID="ID_1730068856" MODIFIED="1410151623660" TEXT="@ResponseBody">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Annotation that indicates a method return value should be bound to the web response body. Supported for annotated handler methods in Servlet environments.
    </p>
    <p>
      As of version 4.0 this annotation can also be added on the type level in which case it is inherited and does not need to be added on the method level.
    </p>
    <p>
      
    </p>
    <p>
      They are annotations of the spring mvc framework and can be used in a controller to implement smart object serialization and deserialization. They help you avoid boilerplate code by extracting the logic of messageconversion and making it an aspect. Other than that they help you support multiple formats for a single REST resource without duplication of code. If you annotate a method with @ResponseBody, spring will try to convert its return value and write it to the http response automatically.
    </p>
  </body>
</html></richcontent>
<node CREATED="1406108424973" ID="ID_1592253041" MODIFIED="1406108501015" TEXT="option">
<font BOLD="true" ITALIC="true" NAME="SansSerif" SIZE="12"/>
</node>
</node>
<node CREATED="1410151535201" ID="ID_1138635177" MODIFIED="1410171222768" TEXT="@RequestBody">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      If you annotate a methods parameter with @RequestBody, spring will try to convert the content of the incoming request body to your parameter object on the fly.
    </p>
    <p>
      
    </p>
    <p>
      http://www.javacodegeeks.com/2013/07/spring-mvc-requestbody-and-responsebody-demystified.html
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="help"/>
</node>
<node CREATED="1406108301118" ID="ID_234160817" MODIFIED="1406108316777" TEXT="prameters">
<node CREATED="1406108325574" ID="ID_1285520473" MODIFIED="1406108326410" TEXT="HttpServletRequest"/>
<node CREATED="1406108331342" ID="ID_1860108160" MODIFIED="1406108332018" TEXT="HttpServletResponse"/>
<node CREATED="1406108518524" ID="ID_1537808706" MODIFIED="1406108519232" TEXT="Model"/>
</node>
</node>
</node>
</node>
<node CREATED="1405503005544" ID="ID_727650834" MODIFIED="1405503028409" POSITION="left" TEXT="Web flow"/>
<node CREATED="1410507715688" ID="ID_641996088" MODIFIED="1410507720443" POSITION="right" TEXT="WS">
<node CREATED="1410507721481" ID="ID_924399895" MODIFIED="1410507780789" TEXT="REST">
<icon BUILTIN="help"/>
<node CREATED="1410507724313" ID="ID_1500303999" MODIFIED="1410507778357" TEXT="ACCEPT_SINGLE_VALUE_AS_ARRAY"/>
<node CREATED="1410507762968" ID="ID_340640644" MODIFIED="1410507837178" TEXT="TypeReference">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      JsonFactory factory = new JsonFactory();
    </p>
    <p>
      ObjectMapper mapper = new ObjectMapper(factory);
    </p>
    <p>
      TypeReference&lt;ArrayList&lt;HashMap&lt;String,String[]&gt;&gt;&gt; typeRef = new TypeReference&lt;ArrayList&lt;HashMap&lt;String,String[]&gt;&gt;&gt;() {};
    </p>
    <p>
      List&lt;Map&lt;String, String[]&gt;&gt; list = mapper.readValue(config.getConfigureValue(), typeRef);
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1406480142577" ID="ID_1019514637" MODIFIED="1410171444082" POSITION="right" TEXT="Log4j">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node CREATED="1406480169524" ID="ID_1986876589" MODIFIED="1406480174021" TEXT="config">
<node CREATED="1406480176150" ID="ID_1086003542" MODIFIED="1406480178667" TEXT="${catalina.base}"/>
</node>
</node>
</node>
</map>
