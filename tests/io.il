main:
	new J, [a, b], r1
	storeglobal r1, j
	loadglobal j, r2
	addi r2, a, r3
	read r3
	loadglobal j, r4
	loadai r4, a, r5
	println r5
	new B, [a, j, b], r6
	storeglobal r6, b
	loadglobal b, r7
	loadai r7, b, r8
	loadai r8, j, r9
	addi r9, a, r10
	read r10
	loadglobal b, r11
	loadai r11, b, r12
	loadai r12, j, r13
	loadai r13, a, r14
	println r14
	addi rarp, local, r15
	read r15
	loadai rarp, local, r0
	println r0
	computeglobaladdress global, r16
	read r16
	loadglobal global, r17
	println r17
	loadi 1, r18
	storeret r18
	jumpi L0
L0:
	ret
