<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
<head>
  <title>Manual de Usuario del Validador CSS</title>
  <link rev="made" href="mailto:www-validator-css@w3.org" />
  <link rev="start" href="./" title="Home Page" />
  <style type="text/css" media="all">
    @import "style/base.css";    
    @import "style/docs.css";
  </style>
  <meta name="revision"
  content="$Id: manual.html.es,v 1.1 2006-12-12 02:28:17 ot Exp $" />
  <!-- SSI Template Version: $Id: manual.html.es,v 1.1 2006-12-12 02:28:17 ot Exp $ -->
</head>

<body>

  <div id="banner">
    <h1 id="title"><a href="http://www.w3.org/"><img height="48" alt="W3C" id="logo" src="http://www.w3.org/Icons/WWW/w3c_home_nb" /></a>
    <a href="./"><img src="images/css_validation_service.png" alt="CSS Validation Service" /></a></h1>
   </div>

<div id="main">
<!-- This DIV encapsulates everything in this page - necessary for the positioning -->

<div class="doc">
<h2>Manual de Usuario del Validador de CSS</h2>

<h3 id="TableOfContents">Tabla de Contenidos</h3>

<div id="toc">
<ul>
  <li><a href="#use">C�mo utilizar el Validador de CSS</a>
    <ul>
      <li><a href="#url">Validaci�n mediante respuesta a URL</a></li>
      <li><a href="#fileupload">Validaci�n mediante carga de archivo</a></li>
      <li><a href="#directinput">Validaci�n mediante entrada directa</a></li>
      <li><a href="#basicvalidation">�Qu� hace la validaci�n b�sica?</a></li>
    </ul>
  </li>
  <li><a href="#advanced">Validaci�n avanzada</a>
    <ul>
	<li><a href="#paramwarnings">Par�metro warnings</a></li>
	<li><a href="#paramprofile">Par�metro profile</a></li>
	<li><a href="#parammedium">Par�metro medium</a></li>
    </ul>
  </li>
  <li><a href="#expert">Para expertos</a>
    <ul>
	<li><a href="#requestformat">Formato de Petici�n de Validaci�n</a></li>
	<li><a href="#api">API de Servicio Web del Validador CSS Validator Web Service API</a></li>
    </ul>
   </li>
</ul>
</div>

<p id="skip"></p>

<h3 id="use">C�mo utilizar el Validador de CSS</h3>

<p>
La manera m�s sencilla de comprobar un documento es utilizar la interfaz b�sica. En esta p�gina 
 encontrar�s tres formularios que corresponden a tres posibilidades:
</p>

<h4 id="url">Validaci�n mediante URL</h4>
<p>
    Simplemente introduce la URL del documento que desees validar.
    Este documento puede ser HTML o CSS.
</p>
<img style="display: block; margin-left:auto; margin-right: auto;" 
    src="./images/uri_basic.png" alt="Validaci�n mediante el formulario URI" />

<h4 id="fileupload">Validaci�n mediante carga de archivo</h4>    
<p>
    Esta soluci�n te permite cargar y comprobar un archivo local. Haz clic en el 
    bot�n "Examinar..." y selecciona el archivo que deseas validar.
</p>
<img style="display: block; margin-left:auto; margin-right: auto;" 
    src="./images/file_upload_basic.png" 
    alt="Validaci�n mediante Carga de Archivo" />
<p>
    En este caso, s�lo se permiten documentos CSS. Esto significa que no puedes
    cargar documentos (X)HTML. Tambi�n debes ser cuidadoso con las reglas 
    @import pues s�lo se seguir�n si referencian expl�citamente a 
    una URL p�blica (de modo que olvida rutas relativas con esta soluci�n)
</p>
   
<h4 id="directinput">Validaci�n mediante entrada directa</h4>
<p>
    Este m�todo es perfecto para probar fragmentos de CSS. S�lo tienes que escribir tu CSS en el textarea
</p>
<img style="display: block; margin-left:auto; margin-right: auto;" 
    src="./images/direct_input_basic.png" 
    alt="Validaci�n mediante entrada directa" />
<p>
    Los mismos comentarios de antes son de aplicaci�n. Advierte que esta soluci�n es 
    muy conveniente si tienes un problema y necesitas ayuda de la comunidad.
    Tambi�n es muy �til para informar de un bug, dado que puedes enlazar a la URL 
    resultante para ofrecer un caso de prueba.
</p>    

<h4 id="basicvalidation">�Qu� hace la validaci�n b�sica?</h4>

<p>    
    Cuando se utiliza la interfaz b�sica, el validador comprobar� el cumplimiento 
    de <a href="http://www.w3.org/TR/CSS2">CSS 2</a>, que es la recomendaci�n t�cnica
    actual de CSS.<br />
    Esto producir� una salida XHTML sin ning�n aviso (�nicamente ver�s los errores).<br />
    El par�metro medium est� fijado en "all", que es el medio adecuado para todos los dispositivos 
    (ver <a href="http://www.w3.org/TR/CSS2/media.html">
    http://www.w3.org/TR/CSS2/media.html</a> para una descripci�n completa de los medios).
</p>

<h3 id="advanced">Validaci�n avanzada</h3>

<p>
    Si necesitas una comprobaci�n m�s espec�fica, puedes utilizar la intervaz avanzada que 
    permite especificar tres par�metros. A continuaci�n tienes una breve ayuda sobre cada uno
    de estos par�metros.
</p>

<h4 id="paramwarnings">Warnings</h4>

<p>
    Este par�metro es �til para ajustar la verbosidad del Validador de CSS. En efecto, 
    el validador puede darte dos tipos de mensajes: errores (errors) y avisos (warnings).
    Los errores ocurren cuando el CSS comprobado no respeta la recomendaci�n CSS.
    Los avisos difieren de los errores en que no suponen un problema referente a la 
    especificaci�n. Se ofrecen para advertir (!) al desarrollador CSS de aquellos 
    puntos que podr�an ser peligrosos y podr�an conducir a un comportamiento extra�o
    en algunos agentes de usuario.
</p>
<p>
    Un aviso t�pico concierne a font-family: si no ofreces una fuente gen�rica, 
    obtendr�s un aviso diciendo que deber�as a�adir una al final de la regla, o 
    de otro modo un agente de usuario que no conozca ninguna de las otras fuentes 
    activar� su fuente predeterminada, lo cual puede resultar en una visualizaci�n 
    extra�a.
</p>

<h4 id="paramprofile">Profile</h4>

<p>
    El validador CSS puede comprobar diferentes perfiles de CSS. Un perfil lista todas las  
    caracter�sticas que se espera que una implementaci�n tenga en una plataforma determinada. 
    Esta definici�n est� tomada del  
    <a href="http://www.w3.org/Style/2004/css-charter-long.html#modules-and-profiles0">
	sitio de CSS
    </a>. La opci�n predeterminada corresponde al, actualmente, m�s utilizado: 
    <a href="http://www.w3.org/TR/CSS2">CSS 2</a>.
</p>

<h4 id="parammedium">Medium</h4>

<p>
    El par�metro medium es el equivalente a la regla @media, aplicada a todo el documento. Encontrar�s m�s informaci�n sobre medios en  
    <a href="http://www.w3.org/TR/CSS2/media.html">
	http://www.w3.org/TR/CSS2/media.html
    </a>.
</p>

<h3 id="expert">S�lo para Expertos</h3>

<h4 id="requestformat">Formato de Petici�n de Validaci�n</h4>
<p>Abajo se encuentra una tabla de los par�metros que pueden usarse para enviar una consulta al Validador de CSS del W3C.</p>

<p>Si deseas utilizar el servidor de validaci�n p�blica del W3C, utiliza los siguientes par�metros en conjunci�n con la siguiente URI base:<br />
<kbd>http://jigsaw.w3.org/css-validator/validator</kbd><br />
(sustituir por la direcci�n de tu propio servidor si deseas llamar a una instancia privada del validador).</p>

<p><strong>Nota</strong>: Si deseas llamar al validador de forma programada para un conjunto de documentos, por favor aseg�rate de que tu script <code>duerma</code> durante <strong>al menos 1 segundo</strong> entre peticiones.
El servicio de validaci�n de CSS es un servicio gratuito y p�blico para todos, tu respeto es apreciado. Gracias.</p>

<table class="refdoc">
  <tbody>
    <tr>
      <th>Par�metro</th>
      <th>Descripci�n</th>
      <th>Valor por defecto</th>
    </tr>
    <tr>
      <th>uri</th>
      <td>El <acronym title="Universal Resource Locator">URL</acronym> del 
        documento a validar. Se permiten documentos CSS y HTML.</td>
      <td>Ninguno, pero debe proporcionarse este par�metro o bien <code>text</code>.</td>
    </tr>
    <tr>
      <th>text</th>
      <td>El documento a validar, s�lo se permite CSS.</td>
      <td>Ninguno, pero debe proporcionarse este par�metro o bien <code>uri</code>.</td>
    </tr>
    <tr>
      <th>usermedium</th>
      <td>Se utiliza <a href="http://www.w3.org/TR/CSS2/media.html">medium</a> para la  
	  validaci�n, como <code>screen</code>, <code>print</code>, <code>braille</code>...</td>
      <td><code>all</code></td>
    </tr>
    <tr>
      <th>output</th>
      <td>Activa los diferentes formatos de salida del validador. Los formatos posibles son 
	<code>text/html</code> y <code>html</code> (documento XHTML, 
	Content-Type: text/html), 
	<code>application/xhtml+xml</code> y <code>xhtml</code> (documento XHTML, Content-Type: application/xhtml+xml), 
	<code>application/soap+xml</code> y <code>soap12</code> (documento SOAP 1.2, Content-Type: application/soap+xml), 
	<code>text/plain</code> y <code>text</code> (documento de texto, 
	Content-Type: text/plain),
	cualquier otro (documento XHTML, Content-Type: text/plain)	
      </td>
      <td>html</td>
    </tr>
    <tr>
      <th>profile</th>
      <td>El perfil de CSS usado para la validaci�n. Puede ser
        <code>css1</code>, <code>css2</code>, <code>css21</code>,
        <code>css3</code>, <code>svg</code>, <code>svgbasic</code>,
        <code>svgtiny</code>, <code>mobile</code>, <code>atsc-tv</code>,
        <code>tv</code> o <code>none</code></td>
      <td>la Recomendaci�n m�s reciente del W3C: CSS 2</td>
    </tr>
    <tr>
      <th>lang</th>
      <td>El lenguaje utilizado para la respuesta, actualmente <code>en</code>,
        <code>fr</code>, <code>ja</code>, <code>es</code>,
        <code>zh-cn</code>, <code>nl</code>, <code>de</code>.</td>
      <td>Ingl�s (<code>en</code>).</td>
    </tr>
    <tr>
      <th>warning</th>
      <td>El nivel de aviso, <code>no</code> para no mostrar ning�n aviso, <code>0</code> 
	para menos avisos, <code>1</code>o <code>2</code> para m�s avisos
      </td>
      <td>2</td>
    </tr>
  </tbody>
</table>

<h4 id="api">API de Servicio Web del Validador de CSS: documentaci�n de la interfaz de validaci�n</h4>
<p>    
    Para obtener m�s ayuda t�cnica, en particular acerca de la salida SOAP 1.2 y todas las formas posibles de llamar al validador, ver la  
    <a href="./api.html">API de Servicio Web del Validador de CSS</a>.       
</p>

</div>
</div>
<!-- End of "main" DIV. -->

   <ul class="navbar"  id="menu">
	<li><strong><a href="./" title="P&aacute;gina de inicio del Servicio de Validaci&oacute;n de CSS del W3C">Inicio</a></strong> <span class="hideme">|</span></li>
	<li><a href="about.html" title="Acerca de este servicio">Acerca de este servicio</a> <span class="hideme">|</span></li>
        <li><a href="documentation.html" title="Documentaci&oacute;n del Servicio de Validaci&oacute;n de CSS del W3C">Documentaci&oacute;n</a> <span class="hideme">|</span></li>
        <li><a href="DOWNLOAD.html" title="Descarga el validador CSS">Descarga</a> <span class="hideme">|</span></li>
        <li><a href="Email.html" title="C&oacute;mo realizar comentarios sobre este servicio">Comentarios</a> <span class="hideme">|</span></li>
        <li><a href="thanks.html" title="Cr&eacute;ditos y Agradecimientos">Cr&eacute;ditos</a><span class="hideme">|</span></li>
      </ul>

   <p id="activity_logos">
      <a href="http://www.w3.org/QA/" title="Actividad de Garant&iacute;a de Calidad del W3C, ofreci&eacute;ndote herramientas Web de calidad libres y m&aacute;s"><img src="http://www.w3.org/QA/2002/12/qa-small.png" alt="QA" /></a><a href="http://www.w3.org/Style/CSS/learning" title="Aprende m&aacute;s sobre Hojas de Estilo en Cascada"><img src="images/woolly-icon" alt="CSS" /></a>
   </p>
   <p id="support_logo">
Apoya esta herramienta, convi&eacute;rtete en un <br />
<a href="http://www.w3.org/Consortium/supporters"><img src="http://www.w3.org/Consortium/supporter-logos/csupporter.png" alt="W3C Supporter" /></a>
   </p>

    <p class="copyright">
      <a rel="Copyright" href="http://www.w3.org/Consortium/Legal/ipr-notice#Copyright">Copyright</a> &copy; 1994-2006
      <a href="http://www.w3.org/"><acronym title="World Wide Web Consortium">W3C</acronym></a>&reg;

      (<a href="http://www.csail.mit.edu/"><acronym title="Massachusetts Institute of Technology">MIT</acronym></a>,
      <a href="http://www.ercim.org/"><acronym title="European Research Consortium for Informatics and Mathematics">ERCIM</acronym></a>,
      <a href="http://www.keio.ac.jp/">Keio</a>),
      All Rights Reserved.
      W3C <a href="http://www.w3.org/Consortium/Legal/ipr-notice#Legal_Disclaimer">liability</a>,
      <a href="http://www.w3.org/Consortium/Legal/ipr-notice#W3C_Trademarks">trademark</a>,
      <a rel="Copyright" href="http://www.w3.org/Consortium/Legal/copyright-documents">document use</a>
      and <a rel="Copyright" href="http://www.w3.org/Consortium/Legal/copyright-software">software licensing</a>

      rules apply. Your interactions with this site are in accordance
      with our <a href="http://www.w3.org/Consortium/Legal/privacy-statement#Public">public</a> and
      <a href="http://www.w3.org/Consortium/Legal/privacy-statement#Members">Member</a> privacy
      statements.
    </p>


  </body>
</html>



