export interface Song {  // <--- this is the interface for a song
id: number;
name: string;
artist: string;
album: string;
duration: number;
}

export const songs: Song[] = [  // <--- this is the array of songs
{
id: 1,
name: 'All I Want',
artist: 'Kodaline',
album: 'In a Perfect World',
duration: 5.05,
},
{ id: 2, name: 'Fix You', artist: 'Coldplay', album: 'X&Y', duration: 4.55 },
{ id: 3, name: 'The Scientist', artist: 'Coldplay', album: 'X&Y', duration: 5.09 },
{ id: 4, name: 'Yellow', artist: 'Coldplay', album: 'Parachutes', duration: 4.32 },
{ id: 5, name: 'Viva la Vida', artist: 'Coldplay', album: 'Viva la Vida or Death and All His Friends', duration: 4.01 },
{ id: 6, name: 'Fix You', artist: 'Coldplay', album: 'X&Y', duration: 4.55 }
];