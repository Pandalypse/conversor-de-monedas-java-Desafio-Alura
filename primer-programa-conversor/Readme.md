
---

## ⚙️ 2️⃣ Cómo subirlo a GitHub

### 👉 Opción A: Desde IntelliJ IDEA
1. Abre el proyecto.
2. Ve a **VCS → Enable Version Control Integration** → selecciona **Git**.
3. Luego, clic en **Commit** (parte inferior) → selecciona todos los archivos y presiona **Commit and Push**.
4. Si es la primera vez:
    - Inicia sesión en GitHub desde IntelliJ.
    - Crea un nuevo repositorio con nombre:  
      `primer-programa-conversor`
5. Confirma con **Push**.

---

### 👉 Opción B: Desde la terminal (más directa)
Abre tu terminal o consola dentro del proyecto y ejecuta:

```bash
git init
git add .
git commit -m "Proyecto Java - Conversor de Monedas con ExchangeRate API"
git branch -M main
git remote add origin https://github.com/TU_USUARIO/primer-programa-conversor.git
git push -u origin main
