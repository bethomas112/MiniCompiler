f:
	loadinargument i, 0, r0
	loadinargument j, 1, r1
	loadglobal b, r4
	loadai r4, a, r5
	loadai r5, a, r6
	loadai r6, a, r7
	loadai r7, a, r8
	storeret r8
	jumpi L0
L0:
	ret
foo:
	loadinargument n, 0, r0
L2:
	loadi 0, r1
	comp r0, r1
	loadi 0, r2
	movgt 1, r2
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
g:
	loadinargument i, 0, r0
	loadinargument j, 1, r1
	loadi 2, r5
	storeret r5
	jumpi L6
L6:
	ret
main:
	loadi 2, r6
	mov r6, r1
L8:
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
	cbreq L9, L11
L9:
	loadi 1, r11
	println r11
	jumpi L11
L11:
L12:
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
	cbreq L13, L14
L13:
	loadi 1, r16
	println r16
	jumpi L15
L14:
	loadi 3, r17
	println r17
	jumpi L15
L15:
L16:
	loadi 1, r18
	compi r18, 1
	cbreq L18, L17
L18:
	loadi 7, r19
	println r19
	jumpi L16
L17:
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
	loadi 2, r25
	new B, [a], r26
	storeoutargument r25, 0
	storeoutargument r26, 1
	call f
	loadret r27
	loadai r27, i, r28
	println r28
	loadi 2, r29
	storeoutargument r29, 0
	call foo
	loadret r30
	println r30
	loadi 0, r31
	storeret r31
	jumpi L7
L7:
	ret
