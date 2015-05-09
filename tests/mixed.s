	.comm globalfoo,8,8
	.section	.rodata
.PRINTSTRING:
	.string "%ld"
.PRINTLNSTRING:
	.string "%ld\n"
.READSTRING:
	.string "%ld"
	.text
.global tailrecursive
	.type	tailrecursive, @function
tailrecursive:
	movq %rdi, r0
L1:
	movq $0, r2
	cmpq r0, r2
	movq $0, r3
	movq $1, r4
	cmovleq r4, r3
	cmpq $1, r3
	je L2
	jmp L4
L2:
	jmp L0
L0:
	addq $8, %rsp
	movq %rbp, %rsp
	popq %rbp
	ret
	.size	tailrecursive, .-tailrecursive
	.text
.global add
	.type	add, @function
add:
	movq %rdi, r0
	movq %rsi, r1
	movq r0, r2
	addq r1, r2
	movq r2, $rax
	jmp L5
L5:
	addq $0, %rsp
	movq %rbp, %rsp
	popq %rbp
	ret
	.size	add, .-add
	.text
.global domath
	.type	domath, @function
domath:
	movq %rdi, r0
	movl $24, %edi
	call malloc
	movq %rax, r4
	movq r4, r1
	movl $8, %edi
	call malloc
	movq %rax, r5
	movq r5, 16(r1)
	movl $24, %edi
	call malloc
	movq %rax, r6
	movq r6, r2
	movl $8, %edi
	call malloc
	movq %rax, r7
	movq r7, 16(r2)
	movq r0, 0(r1)
	movq $3, r8
	movq r8, 0(r2)
	movq 0(r1), r9
	movq 16(r1), r10
	movq r9, 0(r10)
	movq 0(r2), r11
	movq 16(r2), r12
	movq r11, 0(r12)
L7:
	movq $0, r13
	cmpq r0, r13
	movq $0, r14
	movq $1, r15
	cmovgq r15, r14
	cmpq $1, r14
	je L9
	jmp L8
L9:
	movq 0(r1), r16
	movq 0(r2), r17
	movq r16, r18
	imulq r17, r18
	movq r18, r3
	movq 16(r1), r19
	movq 0(r19), r20
	movq r3, r21
	imulq r20, r21
	movq 0(r2), r22
	movq r21, %rax
	movq r21, %rdx
	sarq $63, %rdx
	idivq r22
	movq %rax, r23
	movq r23, r3
	movq 16(r2), r24
	movq 0(r24), r25
	movq 0(r1), r26
	movq r25, %rdi
	movq r26, %rsi
	call add
	movq %rax, r27
	movq r27, r3
	movq 0(r2), r28
	movq 0(r1), r29
	movq r28, r30
	subq r29, r30
	movq r30, r3
	movq $1, r31
	movq r0, r32
	subq r31, r32
	movq r32, r0
	jmp L7
L8:
	movq r1, %rdi
	call free
	movq r2, %rdi
	call free
	jmp L6
L6:
	addq $24, %rsp
	movq %rbp, %rsp
	popq %rbp
	ret
	.size	domath, .-domath
	.text
.global objinstantiation
	.type	objinstantiation, @function
objinstantiation:
	movq %rdi, r0
L11:
	movq $0, r2
	cmpq r0, r2
	movq $0, r3
	movq $1, r4
	cmovgq r4, r3
	cmpq $1, r3
	je L13
	jmp L12
L13:
	movl $24, %edi
	call malloc
	movq %rax, r5
	movq r5, r1
	movq r1, %rdi
	call free
	movq $1, r6
	movq r0, r7
	subq r6, r7
	movq r7, r0
	jmp L11
L12:
	jmp L10
L10:
	addq $8, %rsp
	movq %rbp, %rsp
	popq %rbp
	ret
	.size	objinstantiation, .-objinstantiation
	.text
.global ackermann
	.type	ackermann, @function
ackermann:
	movq %rdi, r0
	movq %rsi, r1
L15:
	movq $0, r2
	cmpq r0, r2
	movq $0, r3
	movq $1, r4
	cmovgeq r4, r3
	cmpq $1, r3
	je L16
	jmp L18
L16:
	movq $1, r5
	movq r1, r6
	addq r5, r6
	movq r6, $rax
	jmp L14
L14:
	addq $0, %rsp
	movq %rbp, %rsp
	popq %rbp
	ret
	.size	ackermann, .-ackermann
	.text
.global main
	.type	main, @function
main:
	movq %rbp, r5
	addq $-8, r5	movq $.READSTRING, %rdi
	movq r5, %rsi
	call scanf
	movq -8(%rbp), r0
	movq %rbp, r6
	addq $-16, r6	movq $.READSTRING, %rdi
	movq r6, %rsi
	call scanf
	movq -16(%rbp), r1
	movq %rbp, r7
	addq $-24, r7	movq $.READSTRING, %rdi
	movq r7, %rsi
	call scanf
	movq -24(%rbp), r2
	movq %rbp, r8
	addq $-32, r8	movq $.READSTRING, %rdi
	movq r8, %rsi
	call scanf
	movq -32(%rbp), r3
	movq %rbp, r9
	addq $-40, r9	movq $.READSTRING, %rdi
	movq r9, %rsi
	call scanf
	movq -40(%rbp), r4
	movq r0, %rdi
	call tailrecursive
	movq $.PRINTLNSTRING, %rdi
	movq r0, %rsi
	call printf
	movq r1, %rdi
	call domath
	movq $.PRINTLNSTRING, %rdi
	movq r1, %rsi
	call printf
	movq r2, %rdi
	call objinstantiation
	movq $.PRINTLNSTRING, %rdi
	movq r2, %rsi
	call printf
	movq r3, %rdi
	movq r4, %rsi
	call ackermann
	movq %rax, r10
	movq $.PRINTLNSTRING, %rdi
	movq r10, %rsi
	call printf
	movq $0, r11
	movq r11, $rax
	jmp L23
L23:
	addq $40, %rsp
	movq %rbp, %rsp
	popq %rbp
	ret
	.size	main, .-main
