(:~ 1 ~:)
for $recibos in //recibo
where $recibos/fecha ="2017-09-05"
return $recibos

(:~ 2 ~:)
(:~ for $detalle in //detalle
where $detalle/codigo = "MAR1"
return $detalle ~:)

(:~ 3 ~:)
(:~ let $c := count(//detalle)
return $c ~:)

(:~ 4 ~:)
(:~ for $detalles in //detalle
order by $detalles/codigo
return $detalles ~:)