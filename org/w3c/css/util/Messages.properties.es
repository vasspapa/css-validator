# Defines your own error and warning message here
content-type: text/html; charset=iso-8859-1
content-language: es

# You can change the level warning like this (example) :
# warning.redefinition.level: 5
#  level is an integer between 0 and 9 (all others values are ignored)
warning.redefinition: Redefinici�n de %s

# used by org.w3c.css.properties.Css1Style
warning.same-colors: Colores iguales para %s y %s
warning.no-color: Hay un color de fondo establecido y no hay color de primer plano
warning.no-background-color: Hay un color de primer plano establecido y no hay color de fondo
warning.color.mixed-capitalization: Aunque los nombres de los colores no son sensibles a las may�sculas, es recomendable utilizar capitalizaci�n mixta para hacerlos m�s legibles: %s
warning.no-generic-family: %s: Es recomendable ofrecer una familia gen�rica como �ltima alternativa
warning.with-space: Los nombres de familias que contengan espacios en blanco deben entrecomillarse. Si no se hace, cualquier espacio \
en blanco anterior o posterior al nombre ser� ignorado y cualquier secuencia de espacios en blanco dentro del nombre \
ser� convertida a un �nico espacio. 
warning.no-padding: Es recomendable tener un �rea de relleno (padding) con el color de fondo
warning.same-colors2: Color de primer plano y color de fondo iguales en dos contextos %s y %s
warning.relative-absolute: Hay algunas longitudes absolutas y relativas en %s. No es una hoja de estilo robusta.

# used by org.w3c.css.properties.CssSelectors
warning.unknown-html: %s no es un elemento de HTML
warning.html-inside: El elemento HTML no puede estar dentro de otro elemento
warning.body-inside: El elemento BODY no puede estar dentro de otro elemento que no sea el elemento HTML
warning.pseudo-classes: La pseudo-clase de Anchor %s s�lo tiene efecto en los elementos 'A'

# not used by org.w3c.css.properties.CssSelectors for the moment
warning.noinside: %s no puede estar dentro de un elemento de l�nea
warning.withblock: Cuidado. Los pseudo-elementos s�lo se pueden unir a elementos de bloque
warning.block-level: Estas propiedad se aplica a elementos de bloque.

# used by org.w3c.css.parser.Frame
warning.no-declaration: No hay declaraciones en la regla

# used by org.w3c.css.values.CssColor
warning.out-of-range: %s est� fuera de rango
error.invalid-color: Funci�n RGB no v�lida

warning.marker: La propiedad marker-offset se aplica a elementos con 'display: marker'

# used by org.w3c.css.properties.ACssStyle
warning.relative: Utilizar unidades relativas da lugar a hojas de estilo m�s robustas en la propiedad %s

# used by org.w3c.css.css.StyleSheetParser and org.w3c.css.css.StyleSheetXMLParser
warning.at-rule: Lo lamentamos, la regla-arroba %s no est� implementada.

# used by all properties and values
error.operator: %s es un operador incorrecto
error.negative-value: Valores negativos de %s no est�n permitidos
error.few-value: Faltan valores para la propiedad %s

# be careful here, values comes first
# You can't write something like this : For the color, blue is an incorrect value
error.value: %s no es un valor de %s

#used by org.w3c.css.properties3.CssToggleGroup
error.groupname: %s no es un nombre de grupo correcto. Use un identificador v�lido

#used by org.w3c.css.properties3.CssGroupReset
error.nogroup: %s no ha sido establecido por la propiedad toggle-group

#used by org.w3c.css.properties3.CssGlyphOrVert
error.anglevalue: El valor tiene que estar comprendido entre -360 y 360, y ser divisible por 90

#used by org.w3c.css.properties3.CssTextKashidaSpace
error.percentage: se espera un valor en porcentaje

#used by org.w3c.css.properties.CssTextAlign
warning.xsl: el valor %s s�lo se aplica a XSL

#used by org.w3c.css.parser.analyzer.CssParser
warning.medialist : la lista de medios (medialist) deber�a comenzar por 'media :' %s
error.nocomb: El combinador %s entre selectores no est� permitido en este perfil o versi�n

#used by org.w3c.css.properties.CssDirection
warning.direction: use la nueva propiedad de CSS3 'writing-mode' en lugar de usar 'direction' para los elementos de bloque

# used by org.w3c.css.properties.CssTextDecoration
error.same-value: %s aparece dos veces

error.generic-family.quote: Los nombres de familia gen�ricos son palabras reservadas y, por tanto, no deben entrecomillarse.

# used by org.w3c.css.properties.CssClip
error.shape: Definici�n de figura no v�lida rect(<top>,<right>,<bottom>,<left>)
error.shape-separator: Separador no v�lido en la definici�n de figura. Debe ser una coma.

# used by org.w3c.css.properties.CssContent
error.attr: Definici�n de attr no v�lida attr(X)
error.function: Definici�n de funci�n no v�lida 
error.counter: Definici�n de contador no v�lida counter(<identifier>[,<list-style-type>]?)
error.counters: Definici�n de contadores no v�lida counters(<identifier>,<string>[,<list-style-type>]?)

# used by org.w3c.css.font.Src
error.format: Definici�n de formato no v�lida format(<string>[,<string>]*)
error.local: Definici�n de localizaci�n no v�lida local(<string>|<ident>+)

# used by org.w3c.css.values.CssAngle, org.w3c.css.values.CssFrequency, org.w3c.css.values.CssTime, org.w3c.css.values.CssLength
error.unit: %s es una unidad incorrecta

# used by org.w3c.css.aural.ACssAzimuth
error.degree: La posici�n debe estar especificada en t�rminos de grados.

# used by org.w3c.css.aural.ACssElevation
error.elevation.range: Especificar la elevaci�n como un �ngulo entre '-90deg' y '90deg'.

# used by org.w3c.css.aural.ACssPitchRange
error.range: El valor est� fuera del rango. Este valor debe estar comprendido entre '0' y '100'.

# used by org.w3c.css.properties.CssTextShadow
error.two-lengths: Un offset de sombra se especifica con dos valores <length> (Opcionalmente, depu�s del offset de sombra puede especificarse un ratio de difuminado.)

error.integer: �ste n�mero debe ser un entero.
error.comma: Falta una coma para separar.

# used by org.w3c.css.values.CssPercentage
error.percent: %s no es un porcentaje correcto

# used by org.w3c.css.values.CssString
error.string: %s no es una cadena correcta

# used by org.w3c.css.values.CssURL
error.url: %s no es un URL correcto

# used by org.w3c.css.values.CssColor
error.rgb: %s no es un color v�lido de 3 o 6 cifras hexadecimales
error.angle: %s no es un �ngulo v�lido. El valor debe estar comprendido entre 0 y 360

# used by org.w3c.css.values.CssNumber
error.zero: �nicamente 0 puede ser un %s. Debe especificarse una unidad detr�s de la cifra

# used by org.w3c.css.parser.CssPropertyFactory
error.noexistence: La propiedad %s no existe
error.noexistence-media: La propiedad %s no existe en el medio %s
warning.noexistence-media: La propiedad %s no existe en el medio %s
warning.notforusermedium : La propiedad %s no existe en �ste medio de usuario
warning.noothermedium : Las propiedades de otros medios podr�an no funcionar en el medio de usuario
# used by org.w3c.css.parser.AtRule*
error.noatruleyet : Las reglas-arroba que no sean @import no son soportadas por CSS1 %s
# used by org.w3c.css.parser.analyzer.CssParser
error.notforcss1 : El valor %s no existe en CSS1

# used by org.w3c.css.parser.CssFouffa
error.unrecognize: Faltan valores o no se reconocen los valores

# used by org.w3c.css.parser.CssFouffa
generator.unrecognize: Error de an�lisis sint�ctico

# used by org.w3c.css.parser.CssSelectors
error.pseudo-element: El pseudo-elemento :%s no puede aparecer aqu� en el contexto %s
error.pseudo-class: La pseudo-clase .%s no puede aparecer aqu� en el contexto de HTML %s
error.pseudo: Pseudo-clase o pseudo-elemento %s desconocido
error.id: �El selector de ID #%s no es v�lido! En un selector simple s�lo puede especificarse un selector de ID: %s.
error.space: Si se utiliza el selector de atributo ~= entonces el valor de %s no puede contener espacios.
error.todo: Lo lamentamos, la caracter�stica %s todav�a no est� implementada.
error.incompatible: %s y %s son incompatibles
error.notformobile : %s no puede usarse en perfiles m�viles
error.notforatsc : %s no puede usarse en perfiles ATSC
error.notfortv : %s no puede usarse en perfiles de televisi�n
error.notversion : %s no puede usarse en �sta versi�n de CSS: %s

error.media: medio no reconocido %s 
error.page: p�gina pseudo-nombrada no reconocida %s

error.unrecognized.link: elemento de enlace o instrucci�n de procesamiento de hoja de estilo xml no reconocida.

# used by StyleSheetGeneratorHTML
generator.context: Contexto
generator.request: Se ha producido un error en el procesado de su hoja de estilo. \
Por favor, corrija su petici�n o env�e un correo a plh@w3.org.
generator.unrecognized: No reconocido
generator.invalid-number: N�mero no v�lido
generator.property: Propiedad no v�lida
generator.line: L�nea
generator.not-found: Archivo no encontrado
generator.doc-html: <p class='vDocHTML'>\
Su hoja de estilo CSS necesita un �rbol de an�lisis del documento v�lido \
para funcionar correctamente. Por tanto debe usar <a href="http://validator.w3.org/check?uri=\
%s">HTML v�lido</a>.</p>

generator.doc:<p class='vDocHTML'>\
Su hoja de estilo CSS necesita un �rbol de an�lisis del documento v�lido \
para funcionar correctamente. Por tanto debe usar <a href="http://validator.w3.org/">HTML \
v�lido</a>.</p>


# used by the parser
parser.semi-colon: Tentativa de encontrar un punto y coma antes del nombre de la propiedad. A��dalo

parser.old_class: En CSS1, un nombre de clase puede empezar por un d�gito (".55ft"), \
excepto si es una magnitud de medida (".55in"). En CSS2, esas clases son interpretadas como \
magnitudes de medida desconocidas (para permitir a�adir nuevas magnitudes en un futuro)


# used by the servlet
servlet.invalid-request: Se ha enviado una petici�n no v�lida.
servlet.process: No se puede procesar el objeto

warning.atsc : %s podr�a no ser compatible con el medio atsc-tv
error.onlyATSC : %s �sta funci�n es s�lo para el medio atsc-tv

warning.otherprofile : la propiedad %s no existe en �ste perfil, pero es v�lida conforme a otro perfil
warning.deprecated : �ste valor est� desaprobado

#used by org.w3c.css.parser.analyzer.CssParser
error.nocomb: La combinaci�n %s entre selectores no est� permitida en �ste perfil o versi�n