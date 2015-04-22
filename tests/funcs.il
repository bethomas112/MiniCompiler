a:
	loadinargument b, 0, r0
	loadinargument c, 1, r1
	loadi 1, r3
	add r0, r3, r4
	mov r4, r2
	loadi 1, r5
	and r1, r5, r6
	storeret r6
	jumpi L0
L0:
	ret
b:
	loadinargument a, 0, r0
	loadinargument b, 1, r1
	loadinargument c, 2, r2
	jumpi L1
L1:
	ret
c:
	loadi 3, r0
	storeret r0
	jumpi L2
L2:
	ret
main:
	loadi 5, r2
	mov r2, r1
	storeoutargument r1, 0
	loadi 1, r3
	storeoutargument r3, 1
	call a
	loadret r4
	mov r4, r0
	loadi 1, r5
	storeoutargument r5, 0
	loadi 2, r6
	storeoutargument r6, 1
	loadi 3, r7
	storeoutargument r7, 2
	call b
	call c
	storeret r1
	jumpi L3
L3:
	ret
