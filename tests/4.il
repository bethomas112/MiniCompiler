g:
	loadinargument i, 0, r0
	loadinargument j, 1, r1
	loadi 3, r5
	storeret r5
	jumpi L0
L0:
	ret
foo:
	loadinargument n, 0, r0
L2:
	loadi 0, r1
	comp r0, r1
	loadi 0, r2
	movle 1, r2
	compi r2, 1
	cbreq L3, L4
L3:
	loadi 1, r3
	storeret r3
	jumpi L1
L4:
	loadi 1, r4
	sub r0, r4, r5
	storeoutargument r5, 0
	call foo
	loadret r6
	mult r0, r6, r7
	storeret r7
	jumpi L1
L1:
	ret
f:
	loadinargument i, 0, r0
	loadinargument j, 1, r1
	loadi 7, r5
	loadglobal b, r6
	loadai r6, a, r7
	loadai r7, a, r8
	loadai r8, a, r9
	storeai r5, r9, i
	loadglobal b, r10
	loadai r10, a, r11
	loadai r11, a, r12
	loadai r12, a, r13
	loadai r13, a, r14
	storeret r14
	jumpi L6
L6:
	ret
bar:
	loadinargument j, 0, r0
	loadinargument i, 1, r1
	jumpi L7
L7:
	ret
main:
	loadi 2, r6
	mov r6, r1
L9:
	loadi 1, r7
	loadi 0, r8
	storeoutargument r7, 0
	storeoutargument r8, 1
	call g
	loadret r9
	comp r1, r9
	loadi 0, r10
	movlt 1, r10
	compi r10, 1
	cbreq L10, L12
L10:
	loadi 1, r11
	println r11
	jumpi L12
L12:
L13:
	loadi 1, r12
	loadi 0, r13
	storeoutargument r12, 0
	storeoutargument r13, 1
	call g
	loadret r14
	comp r1, r14
	loadi 0, r15
	movgt 1, r15
	compi r15, 1
	cbreq L14, L15
L14:
	loadi 1, r16
	println r16
	jumpi L16
L15:
	loadi 3, r17
	println r17
	jumpi L16
L16:
L17:
	loadi 1, r18
	compi r18, 1
	cbreq L19, L18
L19:
	loadi 7, r19
	println r19
	jumpi L17
L18:
	loadi 1, r21
	new B, [a], r22
	storeoutargument r21, 0
	storeoutargument r22, 1
	call g
	loadret r23
	new B, [a], r24
	storeoutargument r23, 0
	storeoutargument r24, 1
	call f
	loadi 1, r25
	println r25
	new B, [a], r26
	loadi 1, r27
	new B, [a], r28
	storeoutargument r27, 0
	storeoutargument r28, 1
	call g
	loadret r29
	storeoutargument r26, 0
	storeoutargument r29, 1
	call bar
	loadi 2, r30
	println r30
	loadi 1, r31
	new B, [a], r32
	storeoutargument r31, 0
	storeoutargument r32, 1
	call g
	loadret r33
	new B, [a], r34
	storeoutargument r33, 0
	storeoutargument r34, 1
	call f
	loadret r35
	loadai r35, i, r36
	println r36
	loadi 0, r37
	storeret r37
	jumpi L8
L8:
	ret
