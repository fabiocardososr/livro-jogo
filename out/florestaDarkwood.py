import subprocess
import logging
import os

# Configuração do log
logging.basicConfig(
    filename="log.txt",
    level=logging.DEBUG,
    format="%(asctime)s - %(levelname)s - %(message)s"
)

try:
    # Caminho absoluto do JAR (opcional, pode usar relativo se preferir)
    jar_path = os.path.join(os.path.dirname(__file__), "FlorestaDarkwood.jar")

    # Comando PowerShell para executar o JAR
    command = f'powershell -window minimized -command "java -jar \\"{jar_path}\\""'

    logging.info(f"Executando comando: {command}")
    subprocess.run(command, shell=True)
    logging.info("Comando executado com sucesso.")

except Exception as e:
    logging.error(f"Erro ao executar o comando: {e}")