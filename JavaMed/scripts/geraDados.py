"""
    geraDados.py
        script para gerar dados aleatórios para o sistema de clinica medica

    os arquivos do banco de dados sao ----> data/pacientes.csv
                                            data/medicos.csv
                                            data/atendentes.csv
                                            data/consultas.csv
"""

from random import choice, randint
from json import load
from sys import argv

banco = {
    "paciente"  : "../data/pacientes.csv",
    "medico"    : "../data/medicos.csv",
    "atendente" : "../data/atendentes.csv",
    "consulta"  : "../data/consultas.csv"
}

# vetor de doencas
# Lista de doenças
doencas = ["Diabetes", "Hipertensão", "Asma", "Bronquite", "Pneumonia", "Tuberculose", "Gripe", "Dengue", "Malária", "Hepatite", "AIDS", "COVID-19", "Febre Amarela", "Sarampo", "Catapora", "Zika", "Chikungunya", "Câncer", "Insuficiência Renal", "Doença de Alzheimer", "Parkinson", "Doença de Crohn", "Lúpus", "Esclerose Múltipla", "Doença Celíaca"]

# arquivos json
nomes = load(open("nomes.json"))
sobre = load(open("sobrenomes.json"))

class GeraDados:

    def __init__(self, debug):
        self.debug = debug

    def geraUsuarios(self, n, tipoUsuario):
        saida = open(banco[tipoUsuario], "w+")

        # vetor de ids para verificar depois
        ids = []

        # id;telefone;cpf;nome;login;senha
        for i in range(n):
            id        = randint(1, 1000)
            # verifica se o ID ja foi criado
            while id in ids:
                id = randint(1, 1000)
            ids.append(id)

            telefone  = randint(100000000, 999999999)
            cpf       = randint(10000000000, 99999999999)
            nome      = f"{choice(nomes)} {choice(sobre)}"
            login     = nome.split(' ')[0]
            senha     = f"{nome.split(' ')[0]}{randint(100, 999)}"

            saida.write(f"{id};{telefone};{cpf};{nome};{login};{senha}\n")
            
            if self.debug:
                print(f'{i+1:02d}. Dados de {nome} gerados')

        saida.close()

    def geraConsultas(self, n):
        saida = open(banco["consulta"], "w+")

        # vetor de ids para verificar depois
        ids = []
        
        for i in range(n):
            id        = randint(1, 1000)
            # verifica se o ID ja foi criado
            while id in ids:
                id = randint(1, 1000)
            ids.append(id)
            
            idPac = randint(1, 1000)
            idMed = randint(1, 1000)
            data  = f"{randint(1, 31):02d}/{randint(1, 12):02d}/{randint(2000, 2020)} {randint(0, 23):02d}:{randint(0, 59):02d}"
            doenca = choice(doencas)

            saida.write(f"{id};{idPac};{idMed};{data};{doenca}\n")

            if self.debug:
                print(f'Consulta {i+1:02d} gerada com id {id}')
        
        saida.close()


def main():
    geraDados = GeraDados(True)

    geraDados.geraUsuarios(10, "paciente")
    geraDados.geraUsuarios(10, "medico")
    geraDados.geraUsuarios(10, "atendente")
    geraDados.geraConsultas(10)


if __name__ == "__main__":
    main()
