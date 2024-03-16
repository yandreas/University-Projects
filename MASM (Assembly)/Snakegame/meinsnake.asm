.model small
.stack 100h

.data
keysmsg db 'Control snake with WASD, quit game with ESC$'
scoremsg db 'Score:  $'
deathmsg db 'Thx for playing!$'
delaytime db 5

Head_X db 40
Head_Y db 12
Snake_Size dw 1

snaketail_x db 300 dup(0)
snaketail_y db 300 dup(0)

scorenum db 4 dup(0)
scorepos db 42

hit db 0
wert1 db 33
wert2 db 213
wert3 db 251
random db 1
random2 db 1
drei db 3
acht db 8
zehn db 10

.code

main proc
mov ax, @data
mov ds, ax ; data in DS schreiben

call background
call snake_start
call keyboard_input

main endp

keyboard_input proc
warte:
mov ah, 0h ; Warteschleife bis
int 16h ;esc gedrueckt wird
cmp al, 77h ;W
je move_up
cmp al, 73h ;S
je move_down
cmp al, 61h ;A
je move_left
cmp al, 64h ;D
je move_right
cmp al, 1bh ;ESC
jne warte
jmp quitesc

move_up:
mov bp, 1
call move

mov ah, 0bh ; Warten auf Keypressed
int 21h      
cmp al, 0
je move_up

mov ah, 0h ; Eingabe verarbeiten
int 16h ;
cmp al, 61h ;A
je move_left
cmp al, 64h ;D
je move_right
cmp al, 1bh ;ESC
je quitesc
jmp move_up

move_down:
mov bp, 2
call move

mov ah, 0bh
int 21h      
cmp al, 0
je move_down

mov ah, 0h
int 16h
cmp al, 61h ;A
je move_left
cmp al, 64h ;D
je move_right
cmp al, 1bh ;ESC
je quitesc
jmp move_down

move_left:
mov bp, 3
call move

mov ah, 0bh
int 21h      
cmp al, 0
je move_left

mov ah, 0h 
int 16h 
cmp al, 77h ;W
je move_up
cmp al, 73h ;S
je move_down
cmp al, 1bh ;ESC
je quitesc
jmp move_left

move_right:
mov bp, 4
call move

mov ah, 0bh
int 21h      
cmp al, 0
je move_right

mov ah, 0h 
int 16h 
cmp al, 77h ;W
je move_up
cmp al, 73h ;S
je move_down
cmp al, 1bh ;ESC
je quitesc
jmp move_right

quitesc:
call death

keyboard_input endp

background proc
call clear_screen

background_mode_colour:
mov ah, 6h
mov al, 0
mov cx, 0000h
mov dh, 25
mov dl, 80
mov bh, 70h ;schwarze Schrift auf grauen Hintergrund
int 10h; bildschirm fuellen

vertical_top:
mov ah, 2h
mov dl, 10
mov dh, 5
mov bh, 00
int 10h ; cursor setzen 

mov ah, 09
mov bh, 00
mov al, '#'
mov cx, 60
mov bl, 70h
int 10h ; zeichen schreiben obere border

mov bp, 14
horizontal_left:
mov ah, 2h
mov dl, 10
inc dh
mov bh, 00
int 10h ; cursor setzen 

mov ah, 09
mov bh, 00
mov al, '#'
mov cx, 1 
mov bl, 70h
int 10h ; zeichen schreiben links
dec bp
jnz horizontal_left

vertical_bottom:
mov ah, 2h
mov dl, 10
mov dh, 19
mov bh, 00
int 10h ; cursor setzen 

mov ah, 09
mov bh, 00
mov al, '#'
mov cx, 59 
mov bl, 70h
int 10h ; zeichen schreiben untere border

mov bp, 14
mov dh, 5
horizontal_right:
mov ah, 2h
mov dl, 69
inc dh
mov bh, 00
int 10h ; cursor setzen 

mov ah, 09
mov bh, 00
mov al, '#'
mov cx, 1 
mov bl, 70h
int 10h ; zeichen schreiben rechts
dec bp
jnz horizontal_right

mov bp, 0
mov dl, 00
mov si, OFFSET keysmsg
keys:
mov ah, 2h
inc dl
mov dh, 00
mov bh, 00
int 10h ; cursor setzen 

mov ah, 09
mov bh, 00
mov al, [si]
mov cx, 1 
mov bl, 70h
int 10h ;
inc si 
inc bp
cmp bp, 43
jne keys

mov bp, 0
mov dl, 35
mov si, OFFSET scoremsg
score:
mov ah, 2h
inc dl
mov dh, 03
mov bh, 00
int 10h ; cursor setzen 

mov ah, 09
mov bh, 00
mov al, [si]
mov cx, 1 
mov bl, 70h
int 10h ;
inc si 
inc bp
cmp bp, 8
jne score

;1. Apfel
mov ah, 6h
mov al, 0
mov ch, 15
mov cl, 50
mov dh, 15
mov dl, 50
mov bh, 40h ;
int 10h; 

ret
background endp

snake_start proc
mov ah, 2h
mov dl, Head_X  
mov dh, Head_Y
mov bh, 00
int 10h 

mov ah, 09
mov bh, 00
mov al, 'x'
mov cx, 1 
mov bl, 70h
int 10h

mov dl, Head_X
mov dh, Head_Y
mov snaketail_x[0], dl
mov snaketail_y[0], dh

mov ah, 2h
mov dl, 43
mov dh, 03
mov bh, 00
int 10h ; cursor setzen 

ret
snake_start endp


move proc
mov si, Snake_Size
cmp si, 1
je remove

tail:
call move_tail

choose:
cmp bp, 1
je up
cmp bp, 2
je down
cmp bp, 3
je left
cmp bp, 4
je right

up:
dec Head_Y
jmp new

down:
inc Head_Y
jmp new

left:
dec Head_X
jmp new

right:
inc Head_X
jmp new

new:
call compare

mov  dl, Head_X  
mov  dh, Head_Y
mov snaketail_x[0], dl
mov snaketail_y[0], dh
mov ah, 2h
mov bh, 00
int 10h

mov ah, 09
mov bh, 00
mov al, 'x'
mov cx, 1 
mov bl, 70h
int 10h 

mov ah, 2h
mov dl, 43
mov dh, 03
mov bh, 00
int 10h ; cursor setzen 

call delay
ret

remove:
mov dl, Head_X
mov dh, Head_Y
mov ah, 2h
mov bh, 00
int 10h 

mov ah, 09
mov bh, 00
mov al, ' '
mov cx, 1 
mov bl, 70h
int 10h 
jmp choose

move endp

move_tail proc
del:
mov dl, snaketail_x[si-1]
mov dh, snaketail_y[si-1]
mov ah, 2h
mov bh, 00
int 10h 

mov ah, 09
mov bh, 00
mov al, ' '
mov cx, 1 
mov bl, 70h
int 10h 


next:

mov dl, snaketail_x[si-2]
mov dh, snaketail_y[si-2]
mov bh, 0    
mov ah, 02h  
int 10h

mov ah, 09
mov bh, 00
mov al, '*'
mov cx, 1 
mov bl, 70h
int 10H 

mov dl, snaketail_x[si-2]
mov dh, snaketail_y[si-2]
mov snaketail_x[si-1], dl
mov snaketail_y[si-1], dh

dec si
cmp si, 1
jne next

ret
move_tail endp

compare proc
mov dl, Head_X  
mov dh, Head_Y
mov snaketail_x[0], dl
mov snaketail_y[0], dh
mov ah, 2h
mov bh, 00
int 10h


mov ah, 08
mov bh, 00
int 10h

cmp ah, 40h
je eat
cmp al, '*'
je true
cmp al, '#'
je true
ret

true:
call death

eat:
call fruit_eat
ret

compare endp

fruit_eat proc
inc Snake_Size
mov di, Snake_Size

mov dl, snaketail_x[di+(-2)]
mov dh, snaketail_y[di+(-2)]
mov snaketail_x[di+(-1)], dl
mov snaketail_y[di+(-1)], dh

call randomnum
call createfruit
call setscore

ret
fruit_eat endp

createfruit proc
mov ah, 6h
mov al, 0
mov ch, random2
mov cl, random
mov dh, random2
mov dl, random
mov bh, 40h ;
int 10h; 

ret
createfruit endp

setscore proc
mov ax, Snake_Size
dec al
call int_to_string

mov scorepos, 42
loop_string_ausgabe:

inc scorepos
dec di

mov ah, 2h
mov dl, scorepos
mov dh, 03
mov bh, 00
int 10h ;  

mov ah, 09
mov bh, 00
mov al, scorenum[di]
mov cx, 1 
mov bl, 70h
int 10h 

cmp di, 0
jne loop_string_ausgabe

ret
setscore endp

int_to_string proc
mov di, 0
L:
mov ah, 0
div zehn
add ah, 48
mov scorenum[di], ah
inc di
cmp al, 0
jne L

ret
int_to_string endp

death proc
mov ax, 0003h
int 10h
mov delaytime, 100
mov dx, 0000h
mov dx, OFFSET DGROUP : deathmsg 
mov ah, 09h ; select print string
int 21h ;
call delay
call clear_screen

mov ah, 4ch ; END, DOS-BOX
int 21h
death endp

randomnum proc
funktion:
mov ah, 00
int 1ah
mov al, dl
mul wert1
add al, wert2
div wert3
mov al, ah
mov ah, 0
div drei
mov random, al

cmp random, 69
jb check_kleiner
jmp funktion

check_kleiner:
cmp random, 10
ja funktion2
jmp funktion

funktion2:
mov ah, 00
int 1ah
mov al, dl
mul wert1
add al, wert2
div wert3
mov al, ah
mov ah, 0
div acht
mov random2, al

cmp random2, 19
jb check_kleiner2
jmp funktion2

check_kleiner2:
cmp random2, 5
ja finish
jmp funktion2

finish:
call checkbody
cmp hit, 1
je funktion
ret
randomnum endp

checkbody proc
mov hit, 0
mov dl, random
mov dh, random2

mov ah, 2h
mov bh, 00
int 10h 

mov ah, 08
mov bh, 00
int 10h

cmp al, '*'
je hitflag
ret

hitflag:
mov hit, 1

ret
checkbody endp

delay proc
mov ah, 00
int 1ah
mov bx, dx
jmp_delay:
int 1ah
sub dx, bx
cmp dl, delaytime
jl jmp_delay

ret
delay endp

clear_screen proc
mov ax, 0003h
int 10h

ret
clear_screen endp

end main