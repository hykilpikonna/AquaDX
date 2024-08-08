export type GameName = 'mai2' | 'chu3' | 'ongeki' | 'wacca'

const multTable = {
  'mai2': [
    [ 100.5, 22.4, 'SSSp' ],
    [ 100.0, 21.6, 'SSS' ],
    [ 99.5, 21.1, 'SSp' ],
    [ 99, 20.8, 'SS' ],
    [ 98, 20.3, 'Sp' ],
    [ 97, 20, 'S' ],
    [ 94, 16.8, 'AAA' ],
    [ 90, 15.2, 'AA' ],
    [ 80, 13.6, 'A' ],
    [ 75, 12, 'BBB' ],
    [ 70, 11.2, 'BB' ],
    [ 60, 9.6, 'B' ],
    [ 50, 8, 'C' ],
    [ 40, 6.4, 'D' ],
    [ 30, 4.8, 'D' ],
    [ 20, 3.2, 'D' ],
    [ 10, 1.6, 'D' ],
    [ 0, 0, 'D' ]
  ],

  // TODO: Fill in multipliers for Chunithm and Ongeki
  'chu3': [
    [ 100.75, 0, 'SSS' ],
    [ 100.0, 0, 'SS' ],
    [ 97.5, 0, 'S' ],
    [ 95.0, 0, 'AAA' ],
    [ 92.5, 0, 'AA' ],
    [ 90.0, 0, 'A' ],
    [ 80.0, 0, 'BBB' ],
    [ 70.0, 0, 'BB' ],
    [ 60.0, 0, 'B' ],
    [ 50.0, 0, 'C' ],
    [ 0.0, 0, 'D' ]
  ],

  'ongeki': [
    [ 100.75, 0, 'SSS+' ],
    [ 100.0, 0, 'SSS' ],
    [ 99.0, 0, 'SS' ],
    [ 97.0, 0, 'S' ],
    [ 94.0, 0, 'AAA' ],
    [ 90.0, 0, 'AA' ],
    [ 85.0, 0, 'A' ],
    [ 80.0, 0, 'BBB' ],
    [ 75.0, 0, 'BB' ],
    [ 70.0, 0, 'B' ],
    [ 50.0, 0, 'C' ],
    [ 0.0, 0, 'D' ]
  ],

  'wacca': [
    [ 100.0, 0, 'AP' ],
    [ 98.0, 0, 'SSS' ],
    [ 95.0, 0, 'SS' ],
    [ 90.0, 0, 'S' ],
    [ 85.0, 0, 'AAA' ],
    [ 80.0, 0, 'AA' ],
    [ 70.0, 0, 'A' ],
    [ 60.0, 0, 'B' ],
    [ 1.0, 0, 'C' ],
    [ 0.0, 0, 'D' ]
  ]
}

export function getMult(achievement: number, game: GameName) {
  achievement /= 10000
  const mt = multTable[game]
  for (let i = 0; i < mt.length; i++) {
    if (achievement >= (mt[i][0] as number)) return mt[i]
  }
  return [ 0, 0, 0 ]
}

export function roundFloor(achievement: number, game: GameName, digits = 2) {
  // Round, but if the rounded number reaches the next rank, use floor instead
  const mult = getMult(achievement, game);
  achievement /= 10000
  const rounded = achievement.toFixed(digits);
  if (getMult(+rounded * 10000, game)[2] === mult[2] && rounded !== '101.0') return rounded;
  return (+rounded - Math.pow(10, -digits)).toFixed(digits);
}
