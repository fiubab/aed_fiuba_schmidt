num: int = 9
lista: list = [1,2,3,4,5,6,7,8,9,10]

def bb_recursiva(buscado: int, lista: list, inicio: int, fin: int ) -> int:
    media: int = (inicio + fin) // 2
    
    if (lista[media] == buscado): 
        return media

    if (buscado < lista[media]):
        return bb_recursiva(buscado, lista, inicio, media - 1)
    else:
        return bb_recursiva(buscado, lista, media + 1, fin)

def busqueda_binaria(buscado: int, lista: list) -> int: 
    if len(lista) == 0:
        return -1
    return bb_recursiva(buscado, lista, 0, len(lista))

print(busqueda_binaria(num, lista))