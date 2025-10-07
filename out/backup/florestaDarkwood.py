import subprocess
import logging
import os
import re
import sys
import ctypes

# Configuração do log
logging.basicConfig(
    filename="log.txt",
    level=logging.DEBUG,
    format="%(asctime)s - %(levelname)s - %(message)s"
)

def mostrar_mensagem(titulo, texto):
    # Exibe uma caixa de mensagem de erro no Windows
    ctypes.windll.user32.MessageBoxW(0, texto, titulo, 0x10)  # 0x10 = MB_ICONERROR

def verificar_versao_java(min_versao=22):
    try:
        resultado = subprocess.run(["java", "-version"], stderr=subprocess.PIPE, stdout=subprocess.PIPE, text=True)
        saida = resultado.stderr if resultado.stderr else resultado.stdout
        logging.info(f"Saída do comando java -version: {saida}")

        match = re.search(r'version \"(\d+)', saida)
        if match:
            versao_principal = int(match.group(1))
            if versao_principal >= min_versao:
                logging.info(f"Versão do Java detectada: {versao_principal}")
                return True
            else:
                mensagem = f"Versão do Java instalada é {versao_principal}. É necessário Java {min_versao} ou superior. A aplicação não poderá ser executada."
                mostrar_mensagem("Versão do Java insuficiente", mensagem)
                logging.warning(mensagem)
                return False
        else:
            mensagem = "Não foi possível detectar a versão do Java instalada. A aplicação não poderá ser executada."
            mostrar_mensagem("Erro na verificação do Java", mensagem)
            logging.error(mensagem)
            return False
    except FileNotFoundError:
        mensagem = "Java não está instalado nesta máquina. A aplicação não poderá ser executada."
        mostrar_mensagem("Java não encontrado", mensagem)
        logging.error(mensagem)
        return False
    except Exception as e:
        mensagem = f"Erro ao verificar a versão do Java: {e}"
        mostrar_mensagem("Erro inesperado", mensagem)
        logging.error(mensagem)
        return False

def executar_jar():
    try:
        jar_path = os.path.join(os.path.dirname(__file__), "FlorestaDarkwood.jar")
        command = f'powershell -window minimized -command "java -jar \\"{jar_path}\\""'
        logging.info(f"Executando comando: {command}")
        subprocess.run(command, shell=True)
        logging.info("Comando executado com sucesso.")
    except Exception as e:
        mensagem = f"Erro ao executar o JAR: {e}"
        mostrar_mensagem("Erro na execução", mensagem)
        logging.error(mensagem)

if __name__ == "__main__":
    if verificar_versao_java(min_versao=22):
        executar_jar()
    else:
        sys.exit(1)