export interface Form {
    id?: string;
    typeForme?: string;
    position?: Position;
    rotation?: number;

    largeur?: number;
    longueur?: number;
    
    rayon?: number;

    cote?: number;
}

export interface Position {
    x?: number;
    y?: number;
}

