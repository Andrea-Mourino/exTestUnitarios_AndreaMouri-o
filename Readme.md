## Examen de Test Unitarios

---

### Enunciado

Con el código de este repositorio realiza:

- (*4 puntos*) Los test unitarios de los **dos** métodos, utiliza test **parametrizados**. Suponiendo que la `string` DNI, siempre es un DNI válido.
- (*2 puntos*) En el código, hay un error, ¿lo pudiste comprabar en los test? ¿Plantea la solución?
- (*3 puntos*) Si no hubiera una comprobación previamente encuanto a la longitud de la `string` del DNI, ¿Qué hay que cambiar en los tests para que comprueben estos casos?

Entrega tu repositorio con el código y los test. Además un `Readme` explicando y justificando cada apartado

Formato del `Readme` *1 punto*

---


# SOLUCIÓN

### Primer Paso

Empezamos haciendo un fork al repositorio del profesor y luego un clone para ir al Intel y darle a la opcion *Clone Repository* y pegar la URL

### Segundo Paso

Creamos el test que en este caso lo hice creando un *Directory* y luego con ese directorio creado darle click derecho y darle *Mark Directory Ass* y seleccionar Test. Otra forma de hacer los test es darle click derecho a la funcion que queremos hacerle test y darle a *Generate* y luego a Test, creandote automaticamente el test

### Tercer Paso

Hacemos los dos test Unitarios y utilizamos test parametrizados (Utilizando el metodo `assertEquals`). Despues de terminar comprobamos que funciona cambiando una letra o numero y que nos salte el error de que esta mal

Aquí el ejemplo:

**Test para comprobarDNI con datos correctos e incorrectos**
```
@ParameterizedTest
@CsvSource({
    "00000000, T, true",
    "12345678, Z, true",
    "87654321, X, true",
    "00000000, A, false",  // Letra incorrecta
    "12345678, A, false",  // Letra incorrecta
    "00000001, R, true",   // Con ceros iniciales
    "00000001, T, false"   // Letra incorrecta
    })
```

**Test para calcularLetraDNI con entradas válidas**

```
@ParameterizedTest
@CsvSource({
    "00000000, T",
    "12345678, Z",
    "87654321, X",
    "00000001, R"  // Verifica que maneja bien los ceros iniciales
    })
```

**Test para entradas inválidas (Errores)**
```
@ParameterizedTest
@CsvSource({
    "'', 'A'",        // DNI vacío
    "'123', 'B'",     // Menos de 8 caracteres
    "'123456789', 'C'", // Más de 8 caracteres
    "'12A45678', 'D'", // Caracter no numérico
    "' 12345678 ', 'Z'" // DNI con espacios
    })
```

**Test para verificar que los espacios en blanco se eliminan correctamente**
```
@Test
void testDniConEspacios() {
    assertEquals('Z', Main.calcularLetraDNI(" 12345678 "));
}
```

**Test con números negativos (debería fallar)**
```
@Test
void testDniNegativo() {
    assertThrows(NumberFormatException.class, () -> {
    Main.calcularLetraDNI("-12345678");
    });
}
```

### Cuarto Paso

El error se encuentra en el método `Integer.parseInt(dni)`, que elimina los ceros iniciales, lo que puede llevar a cálculos incorrectos. Además que en el código original, hay un problema si el DNI tiene ceros a la izquierda.

La solucion es que, en lugar de `Integer.parseInt(dni)`, se debe usar `Long.parseLong(dni)` para DNIs más grandes o manejarlo como `BigInteger`.

### Quinto Paso

Si no hay una validación de longitud del DNI antes de ejecutarlo, los test deben considerar estos casos:

````
@ParameterizedTest
@CsvSource({
    "'', 'A'",       // Vacío
    "'123', 'B'",    // Menos de 8 caracteres
    "'123456789', 'C'", // Más de 8 caracteres
    "'12A45678', 'D'"  // Caracteres no numéricos
    })
void testDniInvalido(String dni, char letra) {
    assertThrows(NumberFormatException.class, () -> {
        Main.calcularLetraDNI(dni);
    });
}
````
Con esto, verificamos que el código maneje correctamente errores de longitud y formato.

### Sexto Paso

De último usamos el *Readme.md* (o creamos uno nuevo) que ya tenía el repositorio al clonarlo y explicamos paso por paso todo el proceso que hemos hecho sin olvidarse de hacer los commits y push.

