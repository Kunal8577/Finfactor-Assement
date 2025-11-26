import React from 'react'

export default function PokemonCard({ pokemon }){
  if (!pokemon) return null

  const img = pokemon?.images?.official_artwork || pokemon?.images?.front_default || ''
  const types = pokemon?.types || []
  const abilities = pokemon?.abilities || []
  const stats = pokemon?.stats || []
  const moves = pokemon?.moves || []

  const capitalized = s => s.charAt(0).toUpperCase() + s.slice(1)

  return (
    <div className="card pokemon-card">
      <div className="card-left">
        <div className="sprite-wrap">
          {img ? (
            <img className="sprite" src={img} alt={pokemon.name} />
          ) : (
            <div className="sprite placeholder" />
          )}
        </div>

        <div className="types">
          {types.map(t => (
            <span key={t} className={`type ${t}`}>{capitalized(t)}</span>
          ))}
        </div>
      </div>

      <div className="card-right">
        <h2 className="title">{capitalized(pokemon.name)} </h2>

        <div className="meta">
          <span>Height: <strong>{pokemon.height}</strong></span>
          <span>Weight: <strong>{pokemon.weight}</strong></span>
          <span>Base XP: <strong>{pokemon.baseExperience}</strong></span>
        </div>

        <div className="two-col">
          <div className="section abilities">
            <h3>Abilities</h3>
            <ul>
              {abilities.map(a => (
                <li key={a.name}>{a.name}{a.is_hidden ? ' (hidden)' : ''}</li>
              ))}
            </ul>
          </div>

          <div className="section stats">
            <h3>Stats</h3>
            <ul>
              {stats.map(s => (
                <li key={s.name}><strong>{s.base}</strong> <span className="stat-name">{s.name}</span></li>
              ))}
            </ul>
          </div>
        </div>

        <h3>Moves</h3>
        <div className="moves">
          {moves.slice(0, 8).map(m => (
            <span key={m} className="move">{m}</span>
          ))}
        </div>
      </div>
    </div>
  )
}
