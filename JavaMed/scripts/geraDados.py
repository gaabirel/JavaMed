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

        # id;nome;cpf;telefone
        for i in range(n):
            id        = randint(1, 1000)
            # verifica se o ID ja foi criado
            while id in ids:
                id = randint(1, 1000)
            ids.append(id)

            nome      = f"{choice(nomes)} {choice(sobre)}"
            cpf       = randint(10000000000, 99999999999)
            telefone  = randint(100000000, 999999999)

            saida.write(f"{id};{nome};{cpf};{telefone}\n")
            
            if self.debug:
                print(f'{i+1:02d}. Dados de {nome} gerados')

        saida.close()

    def geraConsultas(self, n, debug):
        saida = open(banco["consulta"], "w+")

        for i in range(n):
            idPac = randint(1, 1000)
            idMed = randint(1, 1000)

        saida.close()

        return


def main():
    geraDados = GeraDados(True)

    geraDados.geraUsuarios(10, "paciente")
    geraDados.geraUsuarios(10, "medico")
    geraDados.geraUsuarios(10, "atendente")
    # geraDados.geraConsultas(10)


if __name__ == "__main__":
    main()
