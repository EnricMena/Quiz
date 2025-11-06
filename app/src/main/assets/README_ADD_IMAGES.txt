Instrucciones para añadir imágenes al proyecto Android (drawable)

Dónde colocar las imágenes:
- Ruta: app/src/main/res/drawable/

Nombres requeridos (usar exactamente estos nombres, todo en minúsculas, sin tildes ni espacios):
- espana.png   -> imagen para la primera pregunta (España)
- suma.png     -> imagen para la segunda pregunta (Suma)
- planeta.png  -> imagen para la tercera pregunta (Planeta)

Formato y recomendaciones:
- Preferible PNG o JPG. Si las imágenes tienen fondo transparente usa PNG.
- Evita caracteres especiales (ñ, espacios, acentos). Usa solo letras [a-z], números y guiones bajos.
- Si tienes varias densidades puedes crear: drawable-mdpi, drawable-hdpi, drawable-xhdpi... pero no es obligatorio.

Cómo añadir las imágenes (opciones):
1) Desde Android Studio (recomendado):
   - Abre el panel "Project" > selecciona "Android" o "Project" view.
   - Navega a app/src/main/res/drawable (si no existe, créala con botón derecho New > Directory).
   - Arrastra y suelta los ficheros `espana.png`, `suma.png`, `planeta.png` dentro de esa carpeta.
   - Android Studio indexará los recursos automáticamente.

2) Copiar archivos usando CMD (Windows):
   - Abre cmd.exe y ejecuta (reemplaza C:\ruta\a\tus\imagenes por la carpeta donde están tus imágenes):
     copy "C:\ruta\a\tus\imagenes\espana.png" "C:\Users\enric\AndroidStudioProjects\Quiz\app\src\main\res\drawable\espana.png"
     copy "C:\ruta\a\tus\imagenes\suma.png" "C:\Users\enric\AndroidStudioProjects\Quiz\app\src\main\res\drawable\suma.png"
     copy "C:\ruta\a\tus\imagenes\planeta.png" "C:\Users\enric\AndroidStudioProjects\Quiz\app\src\main\res\drawable\planeta.png"
   - Luego vuelve a Android Studio y sincroniza (Build > Clean Project / Rebuild) si es necesario.

Cómo referenciarlas en XML (layout):
- Ejemplo con ImageView:
  <ImageView
    android:id="@+id/imgPregunta1"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@drawable/espana" />

Cómo referenciarlas en código (Kotlin):
- Kotlin:
  val img = findViewById<ImageView>(R.id.imgPregunta1)
  img.setImageResource(R.drawable.espana)

- Java:
  ImageView img = findViewById(R.id.imgPregunta1);
  img.setImageResource(R.drawable.espana);

Siguientes pasos que puedo hacer por ti (elige una):
- A) Pegar estas ImageView en un layout existente (dime cuál y lo edito).
- B) Añadir código para asignar las imágenes dinámicamente según la pregunta.
- C) Nada más: tú subes las imágenes y me avisas para verificar.

