# Guide d'Utilisation de l'Application Mobile

## Étapes pour Exécuter l'Application Mobile

### 1. Installation de Ngrok

1. Installer Ngrok à partir de ce [lien](https://ngrok.com/download).
2. Exécuter votre serveur et obtenir le numéro de port (par exemple 8000).
3. Dans l'invite de commande (cmd), naviguer vers le dossier où `ngrok.exe` se trouve.
4. Exécuter la commande suivante pour obtenir une URL publique gratuite pour héberger le serveur :
    ```sh
    ngrok http 8000
    ```
5. Ajouter cette URL dans `Const.kt` dans le projet Kotlin.
6. Ajouter également cette URL dans `ALLOWED_HOSTS` dans `settings.py` du projet Django.

### 2. Configuration d'Elasticsearch

1. Exécuter Elasticsearch dans votre cmd avec la commande suivante :
    ```sh
    elasticsearch
    ```

### 3. Exécution de l'Application

1. Installer un émulateur et confirmer qu'il est connecté à Internet.
2. Alternativement, vous pouvez utiliser votre téléphone en mode développement.

---


