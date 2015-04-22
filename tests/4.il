main:
	loadi 2, r6
	mov r6, r1
L9:
	loadi 1, r7
	storeoutargument r7, 0
	loadi 0, r8
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
	storeoutargument r12, 0
	loadi 0, r13
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
	storeoutargument r21, 0
	new B, [a], r22
	storeoutargument r22, 1
	call g
	loadret r23
	storeoutargument r23, 0
	new B, [a], r24
	storeoutargument r24, 1
	call f
	loadi 1, r25
	println r25
	new B, [a], r26
	storeoutargument r26, 0
	loadi 1, r27
	storeoutargument r27, 0
	new B, [a], r28
	storeoutargument r28, 1
	call g
	loadret r29
	storeoutargument r29, 1
	call bar
	loadi 2, r30
	println r30
	loadi 1, r31
	storeoutargument r31, 0
	new B, [a], r32
	storeoutargument r32, 1
	call g
	loadret r33
	storeoutargument r33, 0
	new B, [a], r34
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
