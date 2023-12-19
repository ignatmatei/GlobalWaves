export interface Playlist {
    id: number;
    name: string;
    songs: number[];
    description?: string;
}
export const playlists: Playlist[] = [
    {
        id: 1,
        name: 'Coldplay',
        songs: [2, 3, 4, 5, 6],
        description: 'Coldplay is a British rock band formed in London in 1996. The four members, lead singer and pianist Chris Martin, lead guitarist Jonny Buckland, bassist Guy Berryman, and drummer Will Champion were at University College London and came together from 1996 to 1998, during which time the band changed names from Pectoralz, to Starfish, then Coldplay.'
    },
    {
        id: 2,
        name: 'Kodaline',
        songs: [1],
        description: 'Kodaline are an Irish rock band. Originally known as 21 Demands, the band adopted their current name in 2012 to coincide with the changing of their music. The group comprises Steve Garrigan, Mark Prendergast, Vincent May and Jason Boland.'
    },
    {
        id : 3,
        name : 'The Script',
        songs : [2, 4],
        description: 'The Script are an Irish rock band formed in 2007 in Dublin, Ireland. They first released music in 2008. It consists of lead vocalist and keyboardist Daniel O\'Donoghue, lead guitarist Mark Sheehan, and drummer Glen Power.'
    },
    {
        id: 4,
        name: 'My first playlist',
        songs: [1, 2, 3],
        description: 'This is my first playlist'
    }
];
// Path: fe/GlobalWaves/src/app/playlist.service.ts