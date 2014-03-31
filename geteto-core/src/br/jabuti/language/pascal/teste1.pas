{ Este e' um programa exemplo
Na verdade, e' um cobaia para este incrivel (?) compilador ISO Pascal }

(* Obserse que ele ja' ignora comentarios! *)

program teste;

const
	a = 3;

type
	a = integer;
	b = record
		a : int;
		b : array [1..5] of array [1..6] of integer;
	end;

var
  a : array [1..3] of integer;

procedure println(a : integer; var b : real);
begin
	a := 3 + (2 / 4) + b[1].teste;
	if (a[1].teste < 5) then begin
		println(a);
		a := 3 + (2 / 4) + b[1].teste
	end
end;

begin
	a := 3 + (2 / 4) + b[1].teste;
	if (a[1].teste < 5) then begin
		println(a);
		a := 3 + (2 / 4) + b[1].teste
	end
end.